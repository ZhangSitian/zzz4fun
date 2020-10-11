package com.zzz.fun.importanno;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target(ElementType.TYPE)
// 需要条件选择时，使用ImportSelector
@Import(MyImportSelector.class)
public @interface EnableMyImport {
    boolean needConfig() default false;
}
