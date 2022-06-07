package com.hnc.sibob.global.config;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.validation.annotation.Validated;

import lombok.Getter;

@Getter
@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class ConfigProperties {

	@NotBlank
	private final String secret;

	public ConfigProperties(String secret) {
		this.secret = secret;
	}
}
