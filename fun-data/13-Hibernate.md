# Hibernate

##ORM框架功能
- 延迟加载 (Lazy loading)：懒加载，不加载关联对象

- 预先抓取( Eager fetching)：预先加载关联对象，抓取的功能可以在一个操
作中将全部数据从数据库中提取出来，这节省了多次查询的成本。

- 级联(Cascading)：更改数据库中的表会同时修改其他表。当删除Order对
象时，我们希望同时在数据库中删除关联的LineItem。

- 缓存

- 分布式缓存

## 附加服务
- Spring声明式事务的集成支持;
- 透明的异常处理 ;
- 线程安全的、轻量级的模板类;
- DAO支持类;
- 资源管理。


