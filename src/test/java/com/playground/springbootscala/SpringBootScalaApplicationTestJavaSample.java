package com.playground.springbootscala;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * this is how spring boot test works in java
 */
@SpringBootTest
@RequiredArgsConstructor
class SpringBootScalaApplicationTestJavaSample {
	private final ConfigurableApplicationContext applicationContext;


	@Test
	void contextLoads() {
	}

}
