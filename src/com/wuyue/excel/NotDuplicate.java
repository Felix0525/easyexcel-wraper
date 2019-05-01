package com.wuyue.excel;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotDuplicate {

    int code() default 1;

    String message() default "Duplicate field";

}
