package com.iware.lottery.admin.auth;

import java.lang.annotation.*;

/**
 * create
 * Created by Mahone Wu on 16/11/5.
 */
@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthValidate {
    boolean validate() default true;
}
