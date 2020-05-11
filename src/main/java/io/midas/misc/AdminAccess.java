package io.midas.misc;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AccessRole//("admin")
public @interface AdminAccess {
    @AliasFor(annotation = AccessRole.class,value="module")
    String abc() default "service";
}
