# Mybatis

标签：mybatis

---

**Contents**

  - [原生jdbc](#原生jdbc)
      - [编程步骤](#编程步骤)
      - [存在问题](#存在问题)
  - [mybatis框架用法](#mybatis框架用法)
    - [mapper代理开发规范](#mapper代理开发规范)
    - [parameterType和resultType](#parameterType和resultType)
    - [输入映射和输出映射](#输入映射和输出映射)
    - [动态sql](#动态sql)

---

## 原生jdbc

#### 编程步骤

1. 加载数据库驱动
2. 创建并获取数据库链接
3. 创建jdbc statement对象
4. 设置sql语句
5. 设置sql语句中的参数(使用preparedStatement)
6. 通过statement执行sql并获取结果
7. 对sql执行结果进行解析处理
8. 释放资源(resultSet、preparedStatement、connection)


#### 存在问题

1.数据库连接，使用时就创建，不使用立即释放，对数据库进行频繁连接开启和关闭，造成数据库资源浪费，影响数据库性能。无法控制上限。
 
设想：使用数据库连接池管理数据库连接。

2.编写sql，向preparedStatement中设置参数，对占位符号位置和设置参数值，如果修改需要重新编译java代码，不利于系统维护。

设想：将sql语句配置在xml配置文件中，即使sql变化，不需要对java代码进行重新编译。

3.从resultSet中遍历结果集数据时，存在硬编码，将获取表的字段进行硬编码，不利于系统维护。
 
设想：将查询的结果集，自动映射成java对象。

## mybatis框架用法

#### mapper代理开发规范

- mapper.xml中namespace就是mapper.java的类全路径。
- mapper.xml中statement的id和mapper.java中方法名一致。
- mapper.xml中statement的parameterType指定输入参数的类型和mapper.java的方法输入参数类型一致
- mapper.xml中statement的resultType指定输出结果的类型和mapper.java的方法返回值类型一致。

#### Mapper.xml的配置

##### parameterType、resultType和resultMap

- `parameterType`在Mapper.xml中通过parameterType指定输入参数的类型
   - 可以简单类型、pojo、hashMap
   - 当有多个参数并用@Param修饰时，可以不写parameterType。
   - 对于综合查询，建议parameterType使用包装的pojo，有利于系统扩展。

- `resultType`在Mapper.xml中通过resultType指定输出结果的类型
    - 查询到的列名和resultType指定的pojo的属性名一致，才能映射成功。
    
- `resultMap`可以通过resultMap 完成一些高级映射。即使查到的列名和映射的pojo的属性名不一致。
    - 将关联查询的列映射到一个pojo属性中。（一对一）
    - 将关联查询的列映射到一个List<pojo>中。（一对多）

##### #{}和${}

 - `#{}`表示一个占位符号;
    
  - `${}`表示一个拼接符号，会引起sql注入，所以不建议使用

####  动态sql
   - if判断，常用场景：当有参数为空时，就不加在SQL中
        ```xml
        <if test="number!=null and number != '' ">
        </if>
        ```
        
   - where条件，当where条件中所有字段都为空时，就不会将`where`加在SQL中
      ```xml
     <where>
     </where>
     ```
     
   - foreach循环
     ```xml
     <foreach collection="list" item="product_id" open="AND id IN (" close=")" separator=",">
         <!-- 每个遍历需要拼接的串 -->
         #{product_id}
     </foreach>
     ```
   
   - sql片段
        ```xml
        <mapper>
             <sql id="query_product_where">
              select * from ....
            </sql>
            <include refid="query_product_where"/>
        </mapper>
        ```
   
   
   
#### 多对多查询总结

将查询用户购买的商品信息明细清单，（用户名、用户地址、购买商品名称、购买商品时间、购买商品数量）

针对上边的需求就使用resultType将查询到的记录映射到一个扩展的pojo中，很简单实现明细清单的功能。

一对多是多对多的特例，如下需求：

查询用户购买的商品信息，用户和商品的关系是多对多关系。

- 需求1：

查询字段：用户账号、用户名称、用户性别、商品名称、商品价格(最常见)

企业开发中常见明细列表，用户购买商品明细列表，

使用resultType将上边查询列映射到pojo输出。

- 需求2：

查询字段：用户账号、用户名称、购买商品数量、商品明细（鼠标移上显示明细）

使用resultMap将用户购买的商品明细列表映射到user对象中。

总结：

使用resultMap是针对那些对查询结果映射有特殊要求的功能，比如特殊要求映射成list中包括多个list。
   
#### resultMap总结
   
   - resultType
      - 作用：将查询结果按照sql列名pojo属性名一致性映射到pojo中。
      - 场合：常见一些明细记录的展示，比如用户购买商品明细，将关联查询信息全部展示在页面时，此时可直接使用resultType将每一条记录映射到pojo中，在前端页面遍历list（list中是pojo）即可。
   
   - resultMap
   	
   使用association和collection完成一对一和一对多高级映射（对结果有特殊的映射要求）。
   
   association：
   
   - 作用：将关联查询信息映射到一个pojo对象中。
   - 场合：为了方便查询关联信息可以使用association将关联订单信息映射为用户对象的pojo属性中，比如：查询订单及关联用户信息。
   
   使用resultType无法将查询结果映射到pojo对象的pojo属性中，根据对结果集查询遍历的需要选择使用resultType还是resultMap。
   	
   collection：
   
   - 作用：将关联查询信息映射到一个list集合中。
   - 场合：为了方便查询遍历关联信息可以使用collection将关联信息映射到list集合中，比如：查询用户权限范围模块及模块下的菜单，可使用collection将模块映射到模块list中，将菜单列表映射到模块对象的菜单list属性中，这样的作的目的也是方便对查询结果集进行遍历查询。如果使用resultType无法将查询结果映射到list集合中。
   
   
####  一级缓存（SqlSession级别的缓存）

- 工作原理

1.sqlSession查询时先去缓存查，查到直接返回；缓存查不到去数据库查，查完后放入缓存。
2.sqlSession执行commit操作（执行插入、更新、删除），清空SqlSession中的一级缓存。

- 开启
    mybatis默认支持一级缓存

####  二级缓存（namespace级别的缓存）

-  二级缓存原理

1.多个SqlSession可以共用二级缓存。
2.只要有commit，清空该mapper下的二级缓存区域的数据。
3.若两个mapper的namespace如果相同，这两个mapper执行sql查询到数据将存在相同的二级缓存区域中。

为了将缓存数据取出执行反序列化操作，因为二级缓存数据存储介质多种多样，不一定在内存。

- 开启
    - 全部开启
        ```xml
        <!-- 开启二级缓存 -->
        <setting name="cacheEnabled" value="true"/>
        ```
    
    - 局部开启
        ```xml
        <mapper namespace="com.zzz.learn.mybatis.dao.UserMapper">
        <!-- 开启本mapper的namespace下的二级缓存-->
        <cache />
        </mapper>
        ```
    
    - 局部禁用
        ```xml
            <select id="findOrderListResultMap" resultMap="ordersUserMap" useCache="false">
              </select>
         ```
     
    - 刷新（清空）缓存
      ```xml
        <insert id="insertUser" parameterType="com.zzz.learn.mybatis.dao.domain.User" flushCache="true">
         </insert>
      ```
      
     - 设置刷新间隔时间flushInterval

- 适用场景

    1.数据写操作较少，数据变化少。
    
    2.用户对于数据的实时性不敏感。

- 局限性

    清空时全部清空，没有局部清空的功能



## mybatis框架浅析

#### mybatis和hibernate本质区别和应用场景

- hibernate

是一个标准ORM框架（对象关系映射）。入门门槛较高的，不需要程序写sql，sql语句自动生成了。对sql语句进行优化、修改比较困难的。

应用场景：适用与需求变化不多的中小型项目，比如：后台管理系统，erp、orm、oa。。

- mybatis

专注是sql本身，需要程序员自己编写sql语句，sql修改、优化比较方便。mybatis是一个不完全的ORM框架，虽然程序员自己写sql，mybatis也可以实现映射（输入映射、输出映射）。

应用场景：适用与需求变化较多的项目，比如：互联网项目。

企业进行技术选型，以低成本高回报作为技术选型的原则，根据项目组的技术力量进行选择。

#### 框架执行过程

1、配置mybatis的配置文件，mybatis.xml（名称不固定）

2、通过配置文件，加载mybatis运行环境，创建SqlSessionFactory会话工厂(SqlSessionFactory在实际使用时按单例方式)

3、通过SqlSessionFactory创建SqlSession。SqlSession是一个面向用户接口（提供操作数据库方法），实现对象是线程不安全的，建议sqlSession应用场合在方法体内。

4、调用sqlSession的方法去操作数据。如果需要提交事务，需要执行SqlSession的commit()方法。

5、释放资源，关闭SqlSession

#### SqlSession使用范围
     
 - SqlSessionFactoryBuilder
 
 通过`SqlSessionFactoryBuilder`创建会话工厂`SqlSessionFactory`将`SqlSessionFactoryBuilder`当成一个工具类使用即可，不需要使用单例管理`SqlSessionFactoryBuilder`。在需要创建`SqlSessionFactory`时候，只需要new一次`SqlSessionFactoryBuilder`即可。
 
 
 - `SqlSessionFactory`
 
 通过`SqlSessionFactory`创建`SqlSession`，使用单例模式管理`sqlSessionFactory`（工厂一旦创建，使用一个实例）。将来mybatis和spring整合后，使用单例模式管理`sqlSessionFactory`。
 
 
 - `SqlSession`
 
 `SqlSession`是一个面向用户（程序员）的接口。SqlSession中提供了很多操作数据库的方法：如：`selectOne`(返回单个对象)、`selectList`（返回单个或多个对象）。
 
 `SqlSession`是线程不安全的，在`SqlSesion`实现类中除了有接口中的方法（操作数据库的方法）还有数据域属性。
 
 `SqlSession`最佳应用场合在方法体内，定义成局部变量使用。
 
 ####  selectOne 和 selectList
 - `selectOne`和`selectList`
 
 `selectOne`表示查询一条记录进行映射，使用`selectList`也可以使用，只不过只有一个对象
 
 `selectList`表示查询出一个列表(参数记录)进行映射，不能够使用`selectOne`查，不然会报下面的错:
 
 ```
 org.apache.ibatis.exceptions.TooManyResultsException: Expected one result (or null) to be returned by selectOne(), but found: 3
 ```




