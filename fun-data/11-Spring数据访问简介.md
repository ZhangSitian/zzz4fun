# Spring数据访问简介
Spring的目标之一就是使开发时能够遵循面向对象原则中的"针对接口编程"。

## 数据访问对象（data access object）
DAO提供了数据读取和写入到数据库中的一种方式。应以接口的方式发布功能。

## 数据源
- BasicDataSource：池化管理。
- DriverManagerDataSource: 在每个连接请求时都会返回一个新建的连接，没有进行池化管理。
- SingleConnectionDataSource：在每个连接请求时都会返回同一个连接。

 ### BasicDataSource
 ```xml
 <bean id= "dataSource" class="org.apache.commons.dbcp.BasicDataSource">
     <property name="driverClassName" value= "org.hsqldb.jdbcDriver" />
     <property name= "url" value="jdbc:hsqldb:hsql://localhost/db/table" />
     <property name= "username" value="sa" />
     <property name= "password" value="" />
     <property name="initialSize" value="5" />
     <property name="maxActive" value="10" />
 </bean>
 ```
  - initialSize
 池启动时创建的连接数量
 - maxActive
 同一时间可从池中分配的最多连接数。0表示无限制
 - maxIdle 
 地里不会被释放的最多空闲连接数。0表示无限制
 - maxOpenPreparedStatements
 在同一时间能够从语句地中分配的预处理语句的最大数量。0表示无限制
 - maxWait
 在抛出异常之前，池等待连接回收的最大时间(当没有可用连接时)，-1表示无限等待
 - minEvictableIdleTimeMillis
 连接在池中保持空闲而不被回收的最大时间
 - minIdle
 在不创建新连接的情况下，池中保持空闲的最小连接数
 - poolPreparedStatements
 是否对预处理语句进行地管理(布尔值)
 
 ## JDBC
 ### 优缺点
   - 优点：
     - JDBC 不要求我们掌握其他框架的查询语言。
     - 它是建立在SQL之上的，而SQL本身就是数据访问语言。
     - 与其他的技术相比，使用JDBC能够更好地对数据访问的性能进行调优。
     - JDBC允许用户使用数据库的所有特性，而这是其他框架不鼓励甚至禁止的。
     - 相对于持久层框架，JDBC能够让我们在更低的层次上处理数据，能够访问和管理数据库中单独的列。
     - 这种细粒度的数据访问方式在很多应用程序中是很方便的。
  - 缺点：
     - 需要负责处理与数据库访问相关的所有事情，其中包含管理数据库资源和处理异常。
     
 ### SQLException的问题
  JDBC需要强制捕获SQLException,但是这个异常却没有出错位置和进行处理方式。
  可能导致抛出SQLException的常见问题包括:
 - 应用程序无法连接数 据库;
 - 要执行的查询有语法错误;
 - 查询中所使用的表和 (或)列不存在;
 - 试图插入或更新的数据违反了数据库的完整性约束。

    




 