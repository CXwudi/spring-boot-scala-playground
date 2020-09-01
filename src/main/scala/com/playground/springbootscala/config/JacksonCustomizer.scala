package com.playground.springbootscala.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.{DeserializationFeature, SerializationFeature}
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 * will be picked up when a bean of [[Jackson2ObjectMapperBuilder]] is gotten
 */
@Configuration
class JacksonCustomizer extends Jackson2ObjectMapperBuilderCustomizer {
  private val log = com.typesafe.scalalogging.Logger[this.type]

  override def customize(jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder): Unit = {
    jacksonObjectMapperBuilder.modules(new EclipseCollectionsModule, DefaultScalaModule)
    jacksonObjectMapperBuilder.featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
    jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL)
    log.debug(s"Our own Spring boot Jackson customizer is applied")

  }
}
