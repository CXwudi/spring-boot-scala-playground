package com.playground.springbootscala.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.eclipsecollections.EclipseCollectionsModule
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

/**
 *
 */
@Configuration
class JacksonConfig extends Jackson2ObjectMapperBuilderCustomizer {
  private val log = com.typesafe.scalalogging.Logger[this.type]

  override def customize(jacksonObjectMapperBuilder: Jackson2ObjectMapperBuilder): Unit = {
    jacksonObjectMapperBuilder.modules(new EclipseCollectionsModule, DefaultScalaModule)
    jacksonObjectMapperBuilder.featuresToEnable(SerializationFeature.INDENT_OUTPUT)
    jacksonObjectMapperBuilder.serializationInclusion(JsonInclude.Include.NON_NULL)
    log.debug(s"Customized Jackson setting is configured")

  }
}
