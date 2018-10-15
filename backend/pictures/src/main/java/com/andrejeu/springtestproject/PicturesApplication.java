package com.andrejeu.springtestproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


/**
 * Entry point for an application.
 * Spring boot application class.
 *
 * */
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class PicturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PicturesApplication.class, args);
	}
}
