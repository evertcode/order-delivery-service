package com.evertcode.order.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class OpenApiConfig {
@Bean
public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
	Contact contact = new Contact();
	contact.setName("Hebert Hernandez");
	contact.setUrl("https://github.com/evertcode");
	contact.setEmail("hebert.hernandez7@gmail.com");

	return new OpenAPI().info(new Info()
					.title("Order Delivery API")
					.version(appVersion)
					.description("This project is an order delivery api. The service allows creating orders, " +
							"listing products and orders with products as well as delivering an order.")
					.contact(contact)
					.license(new License().name("GPT 2")
							.url("https://github.com/openai/gpt-2/blob/master/LICENSE")));
}
}
