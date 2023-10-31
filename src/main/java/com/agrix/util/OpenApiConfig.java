package com.agrix.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Open Api configuration class.
 */
@OpenAPIDefinition(
  info = @Info(
    title = "Agrix API - bernmp-dev",
    version = "1.0",
    contact = @Contact(
      name = "Agrix",
      email = "bernardomp.dev@gmail.com",
      url = "www.github.com/bernmp-dev"
    ),
    description = "Open Api documentation for a farm management system.",
    summary = "Farm management system"
  ),
  servers = {
    @Server(
    description = "Localhost",
    url = "http://localhost:8080"
  )
  }
)
@SecurityScheme(
  name = "bearerAuth",
  description = "JWT token",
  scheme = "bearer",
  type = SecuritySchemeType.HTTP,
  in = SecuritySchemeIn.HEADER,
  bearerFormat = "JWT"
)
public class OpenApiConfig {
}
