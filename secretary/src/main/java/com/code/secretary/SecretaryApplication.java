package com.code.secretary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SecretaryApplication extends SpringBootServletInitializer {

	Logger logger = LoggerFactory.getLogger(SecretaryApplication.class);

	@Value("${version.number}")
	private String versionNumber;

	public SecretaryApplication() {
	}

	public static void main(String[] args) {
		SpringApplication.run(SecretaryApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder) {
		return springApplicationBuilder.sources(SecretaryApplication.class);
	}

}
