package com.agrix.util;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration class.
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {
  @Autowired
  private SecurityFilter securityFilter;

  /** Security filter chain. */
  @Bean
  public SecurityFilterChain securityFilterChain(
      HttpSecurity httpSecurity) throws Exception {
    return httpSecurity
      .csrf(AbstractHttpConfigurer::disable)
      .sessionManagement(session -> session
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
          .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
          .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
          .requestMatchers(HttpMethod.POST, "/persons").permitAll()
          .anyRequest().authenticated())
          .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
          .build();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  private SecurityScheme createApiKeyScheme() {
    return new SecurityScheme().type(SecurityScheme.Type.HTTP)
      .bearerFormat("JWT")
      .scheme("bearer");
  }

  /** OpenAPI configuration. */
  @Bean
  public OpenAPI openApi() {
    return new OpenAPI().addSecurityItem(new SecurityRequirement()
      .addList("Bearer Authentication"))
      .components(new Components()
        .addSecuritySchemes("Bearer Authentication", createApiKeyScheme()))
      .info(new Info().title("AGRIX REST API")
      .description("Essa é uma solução em Spring Boot para o setor agrícola, "
        + "focando em eficiência e sustentabilidade. Implementei APIs RESTful "
        + "para gerenciamento de fazendas e plantações, utilizei Spring Data JPA "
        + "para persistência de dados e gerenciamento de erros com Spring Web. "
        + "A aplicação foi empacotada em um ambiente Docker para fácil distribuição e implantação.")
      .version("1.0")
      .contact(new Contact().name("Bernardo").email("bernardomp.dev@gmail.com")));
  }
}