package com.teraha.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;

@Configuration
@EnableWebFluxSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(
            ServerHttpSecurity http) {

        return http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .securityContextRepository(
                NoOpServerSecurityContextRepository.getInstance())
			.authorizeExchange(auth -> auth

					//Documentation
					.pathMatchers(
						"/swagger-ui.html",
						"/swagger-ui/**",
						"/v3/api-docs/**",
						"/v3/api-docs.yaml",
						"/products/v3/api-docs",
						"/purchases/v3/api-docs",
						"/sales/v3/api-docs",
						"/clients/v3/api-docs",
						"/security/v3/api-docs"
						).permitAll()

					//Login
					.pathMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.pathMatchers(HttpMethod.POST, "/auth/login").permitAll()

					//All have to auth
					.anyExchange().authenticated())

					.oauth2ResourceServer(oauth2 ->
							oauth2.jwt(Customizer.withDefaults()))
					.build();
			}
}
