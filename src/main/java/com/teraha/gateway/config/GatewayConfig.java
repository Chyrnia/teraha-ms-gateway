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
			.route("security-auth", route -> route
					.path("/auth/**")
					.uri("lb://SECURITY"))
			.route("security-users", route -> route
					.path("/users", "/users/**")
					.uri("lb://SECURITY"))
			.route("security-docs", route -> route
					.path("/security/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://SECURITY"))
			.route("products-docs", route -> route
					.path("/products/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://PRODUCTS"))
			.route("purchases-docs", route -> route
					.path("/purchases/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://PURCHASES"))
			.route("sales-docs", route -> route
					.path("/sales/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://SALES"))
			.route("clients-docs", route -> route
					.path("/clients/v3/api-docs")
					.filters(f -> f.stripPrefix(1))
					.uri("lb://SALES"))
			.route("products", route -> route
					.path("/products/**")
					.uri("lb://PRODUCTS"))
			.route("purchases", route -> route
					.path("/purchases/**")
					.uri("lb://PURCHASES"))
			.route("suppliers", route -> route
					.path("/suppliers/**")
					.uri("lb://PURCHASES"))
			.route("sales", route -> route
					.path("/sales/**")
					.uri("lb://SALES"))
			.route("clients", route -> route
					.path("/clients/**")
					.uri("lb://SALES"))
			.build();

	}
}
