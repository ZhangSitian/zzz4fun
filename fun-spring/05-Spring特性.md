# Spring特性

## 2.5新特性
2007年11月，Spring框架2.5版本发布。
Spring 2.5的重大意义在于拥抱注解驱动开发，Spring 2.5之前版本都是采用基于
XML的配置。Spring 2.5引人了几种使用注解的方式，显著减少了配置Spring所需要
的XML信息。
 - 使用@Autowired实现基于注解驱动的依赖注入和使用@Qualifier实现
细粒度的自动装配( auto-wiring)控制。
 - 支持JSR-250注解，包括支持命名资源依赖注入的@Resource,以及对生命
周期方法支持的@PostConstruct和@PreDestroy。
 - 自动扫描使用@Component注解(或其他构造型注解)所标注的Spring组件。
 - 一个全新的基于注解驱动的SpringMVC编程模型，极大简化了SpringWeb
开发。
 - 基于 JUnit4和注解的一个新的集成测试框架。
 - 完全支持Java 6和Java EE5，涵盖JDBC4.0、JTA 1.1、 JavaMail 1.4和JAX-
WS 2.0 ;
 - 通过Bean的名字来编织切面的新的Bean命名切入点表达式;
内嵌支持AspectJ的类加载器织入;
 - 新的 XML命名空间，包括配置应用上下文细节的context命名空间和配置
消息驱动Bean的jms命名空间;
 - 支持在Sq1JdbcTemplate中使用命名参数。
 
 ## 3.0新特性
 - Spring MVC全面支持Rest, Spring MVC控制器响应REST风格的URL并返
    回XML、JSON、RSS或者其他适宜的响应。
 - 新的表达式语言把Spring的依赖注入带到了一个新的高度，允许注入各种来源,包含
 其他Bean和系统属性。
 - Spring MVC新的注解，包含@CookieValue和@RequestHeader, 分别
 从Cookie和请求头中获取值。
 - 一个新的 XML命名空间，用来减少Spring MVC配置。
 - 通过注解驱动声明异步和调度方法。
 - 一个新的注解驱动的配置模型， 几乎可以避免使用XML进行Spring配置，
 我们将在下一章节探讨新的配置风格。
 - SpringWebService项目的OXM功能已被迁移到Spring框架核心。
 Spring 3.0废弃了哪些特性和Spring 3.0拥有哪些新特性- "样重要。 特别需要指出
 的是，从3.0版本开始, Spring要求Java 5或更高版本，Java 1.4已经完成了它的使命,
 Spring不再支持Java 1.4。
