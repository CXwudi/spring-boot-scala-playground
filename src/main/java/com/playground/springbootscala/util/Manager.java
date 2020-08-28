package com.playground.springbootscala.util;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * A special {@link Component} that is stateful, or it can hold some variables
 * @author CX无敌
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Manager {
}
