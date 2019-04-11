package com.etop.management.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Alan.
 *
 * 此注解用来标记非数据库字段
 *
 * @author 何利庭
 * @DATE 2016/9/1
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotDatabaseField {
}
