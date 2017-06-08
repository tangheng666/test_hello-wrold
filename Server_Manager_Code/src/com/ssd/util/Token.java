package com.ssd.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Token {   //定义一个toke 注解
	
	boolean save() default false;
	
	boolean remove() default false;
	

}
