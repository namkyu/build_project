package com.release.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @FileName : Action.java
 * @Project : build_project
 * @Date : 2012. 3. 20.
 * @작성자 : 이남규
 * @프로그램설명 :
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {

	public String value() default "";

}
