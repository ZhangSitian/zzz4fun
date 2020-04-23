package com.zzz.learn.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 可以定义多个组,按业务区分接口组
     */
    @SuppressWarnings("unchecked")
    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("user")//不能写中文
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(true)
                .pathMapping("/")
                .select()
                .paths(or(regex("/restapi/user.*")))//过滤的接口
                .build()
                .apiInfo(userApiInfo());
    }

    @SuppressWarnings("unchecked")
    @Bean
    public Docket orderApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("order")
                .genericModelSubstitutes(DeferredResult.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .pathMapping("/")
                .select()
                .paths(or(regex("/restapi/order.*")))//过滤的接口
                .build()
                .apiInfo(userApiInfo());
    }

    private ApiInfo userApiInfo() {
        ApiInfo apiInfo = new ApiInfo("猿天地",//大标题
                "欢迎来到猿天地学习",//小标题
                "0.1",//版本
                "",
                "yinjihuan",//作者
                "猿天地官网",//链接显示文字
                "http://cxytiandi.com"//网站链接
        );
        return apiInfo;
    }
}
