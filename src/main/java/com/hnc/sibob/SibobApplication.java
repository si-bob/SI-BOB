package com.hnc.sibob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.hnc.sibob.global.config.ConfigProperties;

@EnableJpaRepositories // https://batory.tistory.com/463?category=1010193
// @EnableScheduling
// @EnableBatchProcessing
// @EnableCaching
@EnableJpaAuditing
@EnableConfigurationProperties(value = {
	ConfigProperties.class})
@SpringBootApplication
public class SibobApplication {

	public static void main(String[] args) {
		SpringApplication.run(SibobApplication.class, args);
	}

}
