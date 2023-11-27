package com.second.main.workbook.springboot.importTestSelector;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * {@code @author}  chouchou
 * {@code @date} 2023/8/22
 * {@code @description} import selector
 */
public class ZooImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.second.project.springboot.importTestSelector.ZooConfig"};
    }
}
