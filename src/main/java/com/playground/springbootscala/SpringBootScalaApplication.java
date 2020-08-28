package com.playground.springbootscala;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * We tried various Scala implementation of this class, none of them can survive
 * {@code mvn clean test} <br/>
 * The issue is spring-boot plugin can not find main class. This might related to the fact that
 * Scala companion object is not really a static method replacer
 * @author CX无敌
 */
@SpringBootApplication
public class SpringBootScalaApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringBootScalaApplication.class, args);
  }
}


