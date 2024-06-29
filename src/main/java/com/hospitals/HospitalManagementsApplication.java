package com.hospitals;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class HospitalManagementsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalManagementsApplication.class, args);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				        .allowedOrigins("http://localhost:3000") // Allow requests from this origin
						.allowedMethods("GET", "POST", "PUT", "DELETE") // Allow these HTTP methods
						.allowedHeaders("*") // Allow all headers
						.allowCredentials(true); // Allow credentials (e.g., cookies)
			}
		};
	}

}
