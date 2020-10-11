# Spring容器

## Spring的容器实现可以归为两种：
   - Bean工厂
       - 提供基本的DI支持
   - 应用上下文
        - 配置文件解析
        - 发布应用事件

## ApplicationContext
Spring通过应用上下文( Application Context )装载Bean的定义并把它们组装起来。
Spring应用上下文全权负责对象的创建和组装。Spring 自带了几种应用上下文的实现，
它们之间主要的区别仅仅是如何加载它们的配置。

### ClassPathXmlApplicationContext
从类路径下的xml配置文件中加载上下文的定义，把应用上下文定义文件当做类资源。

### FileSystemXmlApplicationContext
读取文件系统下的xml配置文件并加载上下文定义。

### XmlWebApplicationContext
读取web应用下的xml配置文件并加载上下文定义。

### AnnotationConfigApplicationContext
读取w注解的配置并加载上下文定义。

