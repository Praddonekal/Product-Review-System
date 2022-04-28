package com.spring.boot.micro;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;


@EnableDiscoveryClient
@SpringBootApplication
public class MicroServiceEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroServiceEurekaServerApplication.class, args);
		
		System.out.println("Eureka Server started to register a micro-service");
	}
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/actuator/info").setViewName("index");
	}
}
