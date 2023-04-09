package com.astocoding.backend.anno;


import com.astocoding.backend.enums.JobCategory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface JobCollectionInstance {
    JobCategory jobCategory();

}
