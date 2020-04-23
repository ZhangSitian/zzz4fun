# Keeyalived 简介

> 给予 ARRP 网络协议

- 两个主机虚拟成一个设备，也就是一个虚拟IP（VIP）
- 拥有虚拟IP的设备位master设备
- 其他设备不能有虚拟IP，都是backup状态的设备，收到master 状态通告之外，不执行任何对外服务，当主机失效时将接管原先的master 的虚拟IP以及对外提供各项服务

- 安装(master-backup都安装)

`# yum -y install keepalived -y`

- 配置：`/etc/keepalived/keepalived.conf`

``` sql
vrrp_script check_run {
    script "/etc/keepalived/check_mysql.sh"
    interval 5
}
```

## keepalived 演示

- 主主配置

1. master 配置
- master: my.cnf

``` sql
auto_increment_increment = 2
auto_increment_offset = 1
```

- 修改global

``` sql
# mysql -uroot -p
mysql> set global auto_increment_increment=2;
mysql> set global auto_increment_offset=1;
```

- 推出

``` sql
# mysql -uroot -p
mysql> show variables like 'auto%'
```

2. backup 配置

- my.cnf

``` sql
auto_increment_increment = 2
auto_increment_offset = 2
```

``` sql
# mysql -uroot -p
mysql> set global auto_increment_increment=2;
mysql> set global auto_increment_offset=2;
```

- 推出

``` sql
# mysql -uroot -p
mysql> show variables like 'auto%'
```

- 查看账号

``` sql
mysql>user mysql
mysql>select user.host from user;
> show variables like '%read_only%'
mysql> show master status \G
```

3. master

``` sql
mysql> change master to master host='192.168.3.101',
master_user='dba_repl',
master_password='123456',
master_log_file = 'mysql-bin.000003',
master_log_pos='xxxx'


master_log_file = 'mysql-bin.000003',
master_log_pos='xxxx'
上面两个值查看 backup 的show master status

> start slave;
> show slave status \G

```

4. keepalived 安装

- master

``` sql
# yum -y install keepalived
```

- backup

``` sql
# yum -y install keepalived
```

``` sql
# cd /etc/keepalived/
# vim keepalived.conf
```

``` sql
vrrp_script check_run {
    script "/etc/keepalived/check_mysql.sh"
    interval 5
}
virtual_ipaddress {
    192.168.3.99/24
}
```

- 两个服务器都有： check_mysql.sh 有执行权限

``` sql
# chmod a+x check_mysql.sh
```

``` sh
#!/bin/bash
MYSQL=which mysql
MYSQL_HOST=127.0.0.1
MYSQL_USER=root
MYSQL_PWD=123456
CHECK_TIME=3
MYSQL_OK=1
function check_mysql_helth() {
    $MYSQL -h$MYSQL_HOST -u$MYSQL_USER -p${MYSQL_PWD} -e "select @@version;" >/dev/null 2>&1
    if [ $? = 0 ]; then
        MYSQL_OK = 1
    else
        MYSQL_OK = 0
    fi
    return $MYSQL_OK
}
while [ $CHECK_TIME -ne 0 ]
do
    let "CHECK_TIME -= 1"
    check_mysql_helth

    echo $MYSQL_OK
if [ $MYSQL_OK = 1 ] ; then
    CHECK_TIME = 0
    exit 0
fi
if [ $MYSQL_OK -eq 0 ] && [ $CHECK_TIME -eq 0 ]
    pkill keepalived
exit 1
fi
```

5. 启动keepalived进程

- master: `# /etc/init.d/keepalived start	`
- slave : `# /etc/init.d/keepalived start	`
- master :`# ip addr show`

6. 模拟master 宕机 : `# /etc/init.d/mysql stop`
7. 查看 vip =>  master: `# ip addr show`

- backup : `# ipaddr show`

## 如何解决读压力大问题

- 读负载和写负载是两个不同的问题
  - (1) 写操作只能在 Maser 数据库上执行
  - (2) 读操作都可以在 Maser 和 Slave 运行

- 相对于写负载，解决读负载要更容易
- 进行读写分离，**主服务器**主要执行**写操作**
- **读**操作的压力平均分摊到不同过的 **Slave** 服务器上

- 增加**前段缓存服务器**如 Redis, Memcache 等

- Redis 可持久化/主从复制/集群

### 如何进行读写分离

1. SQL语句连接不同的服务器
    - 优点：完全有开发人员控制，实现更加的灵活
    - 由程序直接连接数据库，所以性能损耗比较少
    - 缺点：实时性要求比较高的数据，就不适合在从库上查询
    - 冯家开发的工作量，是程序代码更加复杂
    - 人为控制，容易出现错误

**库存必须在主库上查询** 超卖情况

2. 由**数据库中间层**完成读写分离
    - DB proxy => keepalived(M<->S)
    - select -> Slave
    - update/Insert/Delete/Create -> Master

    - 优点
    - 由中间件根据查询语法分析，自动完成读写分离
    - 对程序透明，对于已有程序不同做任何调整

    - 缺点：
    - 增加了中间层，查询效率有损耗
    - 对于延迟敏感业务无法自动在主库执行

    - DB Proxy 产品
    - mysql proxy
    - maxscale
    - one proxy
    - proxySQL

### 常用的都服务器负载均衡方式

- 数据库中间层： 数据库中间层读写分离
- DNS 轮询
- LVS(四层代理) / Haproxy(七层代理)
- F5(硬件)

### LVS 读服务器负载均衡

- 四层代理，只进行分发，处理效率更高
- 工作稳定，可进行高可用配置
- 无流量，不会对主机的网络IO造成影响

- Master DB: 192.168.3.100
- Master Backup DB: 192.168.3.101
- Slave DB: 193.168.3.102

- keepalived vip: 192.168.3.99

- lvs manager: 192.168.3.100/101
- lvs vip: 192.168.3.98

#### 配置 LVS

- kernel 2.6 继承LVS 继承软件

- 安装 LVS 管理工具: lvs manager: 192.168.3.100/101
- `yum install -y ipvsadm`

- 192.168.3.100/101/102 三台主机都运行
`# modprobe ip_vs` ip_vs 模块的加载

- 101 LVS 脚本编写
`# vim /etc/inid.d/lvsrs`

``` sh
#!/bin/bash
VIP=192.168.3.98
. /etc/rc.d/init.d/functions
case "$1" in
start)
/sbin/ifconfig lo down
/sbin/ifconfig lo up
echo "1" > /proc/sys/net/ipv4/conf/lo/arp_ignore
echo "2" > /proc/sys/net/ipv4/conf/lo/arp_announce
echo "1" > /proc/sys/net/ipv4/conf/all/arp_ignore
echo "2" > /proc/sys/net/ipv4/conf/all/arp_announce
/sbin/sysctl -p >/dev/null 2>&1
/sbin/ifconfig lo:0 $VIP netmask 255.255.255.255 up
/sbin/route add -host $VIP dev lo:0
echo "LVS-DR real server starts successfully.\n"
;;
stop)
/sbin/ifconfig lo:0 down
/sbin/route del $VIP >/dev/null 2>&1
echo "0" >/proc/sys/net/ipv4/conf/lo/arp_ignore
echo "0" >/proc/sys/net/ipv4/conf/lo/arp_announce
echo "0" > /proc/sys/net/ipv4/conf/all/arp_ignore
echo "0" > /proc/sys/net/ipv4/conf/all/arp_announce
echo "LVS-DR read server stopped."
;;
status)
isLoOn=`/sbin/ifconfig lo:0 | grep "$VIP"`
isRoOn=`/bin/netstat -rn | grep "$VIP"`
if [ "$isLoOn" == "" -a "$isRoOn" == "" ]; then
echo "LVS-DR real server has to run yes."
else
echo "LVS-DR real server is running."
fi
exit 3
;;
`*)`
echo "Usage: $0 {start|stop|status}"
exit 1
esac
exit 0`
```

``` sh
101]# /etc/init.d/lvsrs start
102]# /etc/init.d/lvsrs start
```