package io.midas.misc;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface MyAliasAnnotation {
    @AliasFor("location")
    String value() default "";

    @AliasFor("value")
    String location() default "";

}
