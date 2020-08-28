# spring-boot-scala-playground

A POC of how to use Spring, Spring Boot, Scala and Mockito to write a RESTful Api Server.  
The POC successed.

## Prerequest

- Java 11

that's all, scala is imported as a maven dependecy, and maven is managed by the `mvnw` script

## How to check code

Simply import pom.xml into your favourite IDE or editor.

## How to build

`./mvnw package` and then you will see the jar file in `target/`

## How to run

`java -jar <the jar file>`, then visit `localhost:8080/?message=<your message>&name=<your name>` to see what this stupid server can do for you 😂

Or you can click the run button in your favourite IDE or editor if it supports
