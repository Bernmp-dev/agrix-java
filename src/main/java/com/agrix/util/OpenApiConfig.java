package com.agrix.util;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

/**
 * Open Api configuration class.
 */
@OpenAPIDefinition(
    info = @Info(
    title = "Agrix API - bernmp-dev",
    version = "1.0",
    description = "Open Api documentation for a farm management system.",
    summary = "Farm management system",
    contact = @Contact(
      name = "Bernmp-dev",
      email = "bernardomp.dev@gmail.com",
      url = "www.github.com/bernmp-dev"
    )
    ),
    servers = {
        @Server(
        description = "Localhost",
        url = "http://localhost:8080"
        )
    },
    security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
    name = "Bearer Authentication",
    description = "JWT token",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    in = SecuritySchemeIn.HEADER,
    bearerFormat = "JWT"
)
public class OpenApiConfig {
}
