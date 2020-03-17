package com.ywl.study.springsecurity.datasource;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurDataSource {
    String name() default "";
}
