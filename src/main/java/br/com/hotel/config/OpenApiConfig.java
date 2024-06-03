package br.com.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
    	return new OpenAPI().info(new Info()
				.title("Hotel Senior")
				.version("v1")
				.description("Hotel Challenge")
				.termsOfService("http://url")
				.license(new License()
						.name("2.0")
						.url("http://url"))
				);
	}

}
