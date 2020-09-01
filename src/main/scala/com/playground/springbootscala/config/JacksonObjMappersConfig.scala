package com.playground.springbootscala.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import org.springframework.context.annotation.{Bean, Configuration, Primary, Scope}
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

@Configuration
class JacksonObjMappersConfig {
  private val log = com.typesafe.scalalogging.Logger[this.type]

  @Bean @Primary
  def springDefaultBuilder(builder: Jackson2ObjectMapperBuilder): ObjectMapper ={
    log.debug("Using Spring Boot default object mapper")
    builder.build()
  }

  @Bean
  @Scope("prototype")
  def yamlMapper(builder: Jackson2ObjectMapperBuilder): ObjectMapper = {
    log.debug(s"An yaml mapper is applied")
    builder.factory(new YAMLFactory)
    builder.build()
  }
}
