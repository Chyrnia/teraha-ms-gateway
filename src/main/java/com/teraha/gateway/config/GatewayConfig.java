package com.teraha.gateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;

@Configuration
public class GatewayConfig {
	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){
		return builder.routes()
			.route("products-docs", route -> route
					.path("/products/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://PRODUCTS"))
			.route("purchases-docs", route -> route
					.path("/purchases/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://PURCHASES"))
			.route("products", route -> route
					.path("/products/**")
					.uri("lb://PRODUCTS"))
			.route("purchases", route -> route
					.path("/purchases/**")
					.uri("lb://PURCHASES"))
			.route("suppliers", route -> route
					.path("/suppliers/**")
					.uri("lb://PURCHASES"))
			.build();

	}
}
