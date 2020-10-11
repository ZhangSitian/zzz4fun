# Spring

 - Testing
    - Test
 - Core Spring container
    - Beans
    - Core
    - Context
    - Expression
    - Context support
 - AOP
    - AOP
    - Aspects
 - Instrumentation
    - Instrument
    - Instrument Tomcat
 - Data access & integration
    - JDBC
    - OXM
    - ORM
    - JMS
    - Transaction
 - Web and remoting
    - Web
    - Servlet
    - Portlet
    - Struts
 

## Java Bean的发展史

   - 1996年12月，sun公司发布JavaBean 1.00-A规范，面向组件，太简易，不实际
   - 1998年3月，sun公司发布EJB 1.0规范，面向服务端，异常复杂
   - Spring成为基于POJO轻量级开发框架的领导者
    
## Spring介绍
   Spring 是为了解决企业级应用开发的复杂性，而且创建的，使用Spring可以让简
   单的JavaBean实现之前只有EJB才能完成的事情。但Spring不仅仅局限于服务器
   端开发,任何Java应用都能在简单性、可测试性和松耦合等方面从Spring 中获益。
    
### Spring 关键策略:
   - 基于POJO 的轻量级和最小侵入性编程;
   - 通过依赖注入和面向接口实现松耦合;
   - 基于切面和惯例进行声明式编程;
   - 通过切面和模板减少样板式代码。
   
### 通过接口实现、继承父类接入框架存在的问题
强迫开发者编写大量冗余代码、应用与框架绑定，并且通常难以编写测试代码。
Spring竭力避免因自身的API而弄乱你的应用代码。
Spring不会强迫你实现Spring规范的接口或继承Spring 规范的类。
相反，在基于Spring构建的应用中，它的类通常没有任何痕迹表明你使用了Spring。
最坏的场景是，一个类或许会使用Spring注解，但它依旧是POJO。





   
   