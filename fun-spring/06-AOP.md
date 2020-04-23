# 面向切面
- 使通用功能层包裹核心业务层。
- 继承和委托是最常见的实现方式。继承常使对象体系耦合加重，变脆弱。

# 术语
 - 通知（Advice）：切面的工作被称为通知。
    - Before——在方法被调用前
    - After——在方法调用后，不论方法执行是否成功
    - After-returning——在方法执行成功后，调用通知
    - After-throwing——在方法抛出异常后调用通知
    - Around——通知包裹了方法，前后都自定义
 - 连接点（JoinPoint）：调用通知的时机叫做连接点。
 - 切点（Pointcut）：被切的位置
 - 切面（Aspect）：切面是通知和切点的结合。
 - 引入（introduction）：设置值
 - 织入（Weaving）：创建新的代理对象的过程
      - 编译时期织入
      - 类加载期织入
      - 运行期织入
      
# Spring AOP
因为Spring基于动态代理，所以Spring只支持方法连接点，不支持字段和构造器的接入点。

Aspect切点表达式：
- arg()     限制连接点匹配参数为指定类型的执行方法
- @args()     限制连接点匹配参数为由指定注解标注的执行方法
- execution()   用于匹配是连接点的执行方法
- this()    限制连接点匹配AOP代理的Bean引用为指定类型的类
- target()      限制连接点匹配目标对象为指定类型的类
- @target()      限制连接点匹配特定的执行对象，这些对象对应的类要具备指定类型的注解
- within()      限制连接点匹配指定的类型
- @within()    限制连接点匹配指定注解锁标注的类型（SpringAOP方法定义在由指定注解标注的类里）
- @annotation   限制匹配带有指定注解的连接点

## \<aop:declare-parents>
\<aop:declare-parents>为已知接口的实现类新增实现
```xml
 <aop:config>
        <aop:aspect>
            <aop:declare-parents 
                    types-matching="com.zzz.learn.interfaces.Performer+"
                    implement-interface="com.zzz.learn.interfaces.Contestant"
                    delegate-ref="delegate"/>
        </aop:aspect>
</aop:config>
```

## \<aop: asepectj-autoproxy> 
 <aop: asepectj-autoproxy> 使用@AspectJ时自动创建代理，如果不加这个配置，代理类将无法生成。切面无法生效。
- <aop: asepectj-autoproxy>仅仅使用@AspectJ注解作为指引来创建基于代理的切面，
但本质上它仍然是一个Spring风格的切面。
- 如果想用AspectJ，必须在运行时使用AspectJ并不依赖Spring来创建基于代理的切面。
- \<aop:aspect>元素和@AspectJ注解都是把一个 POJO转变为一个切面的有效方式。
但<aop: aspect>相对于@AspectJ的一个明显优势是不需要实现切面功能的源码。

## asepectj 与 Spring配合使用 TODO
