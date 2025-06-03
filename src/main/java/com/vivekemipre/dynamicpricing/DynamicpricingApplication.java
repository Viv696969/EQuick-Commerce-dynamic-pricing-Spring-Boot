package com.vivekemipre.dynamicpricing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class DynamicpricingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DynamicpricingApplication.class, args);
	}

}
