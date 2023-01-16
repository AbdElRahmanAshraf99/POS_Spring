package com.pos.Generator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldInfo
{
	boolean required() default false;

	boolean embeddable() default false;

	HTMLInputType inputType() default HTMLInputType.TEXT;

	boolean system() default false;

	int order() default Integer.MAX_VALUE;
}
