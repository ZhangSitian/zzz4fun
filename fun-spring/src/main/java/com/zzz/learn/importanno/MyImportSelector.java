package com.zzz.learn.importanno;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

// 允许根据条件动态选择想导入的配置类
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        boolean obj = (boolean) annotationMetadata.getAnnotationAttributes(EnableMyImport.class.getName()).get("needConfig");
        System.out.println(obj);
        // 不能返回null，否则会报空指针异常
        return new String[]{ImportConfig.class.getName()};
    }
}
