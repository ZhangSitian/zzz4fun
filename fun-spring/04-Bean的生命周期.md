# Bean的生命周期
   - 实例化
   - 填充属性
   - 调用BeanNameAware的setBeanName方法
   - 调用BeanFactoryAware的setBeanFactory方法
   - 调用ApplicationContextAware的setApplicationContext方法
   - 调用BeanPostProcessors的postProcessBeforeInitialization接口方法
   - 调用InitializingBean的afterPropertiesSet方法
   - 调用定制的初始化方法
   - 调用BeanPostProcessors的后初始化方法
   - 使用Bean
   - 容器关闭
   - 调用DisposableBean的destroy方法
   - 调用定制的销毁方法
   
   ##bean的初始化和销毁
   - init-method和destroy-method
        -  init-method="turnOnLights" // 该方法在实例化bean后调用
        - destroy-method="turnOffLights" // 该方法在bean移出容器和销毁前调用
   
   - InitializingBean和DisposableBean
        - bean实现InitializingBean接口的afterPropertiesSet()
        - bean实现DisposableBean接口的destroy()

   实现接口的缺点是Bean与Spring的API产生了耦合。所以推荐使用
   init-method和destroy-method属性来初始化和销毁Bean。
    
   如果所有bean都用有同样的初始化和销毁方法，可以在Beans中添加
   default-init-method和default-destroy-method。