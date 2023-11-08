package com.harvesthub.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.harvesthub.models.entities.Person;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;

/**Service layer class for handling tokens business logic. */
@Service
public class TokenService {

  private final String secret = "a1b2c3";

  /** Generates a token for a given person. */
  public String generateToken(Person person) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.create()
            .withIssuer("HarvestHub")
            .withSubject(person.getUsername())
            .withExpiresAt(generateExpirationDate())
            .sign(algorithm);
  }

  /** Generates a token expiration date. */
  private Instant generateExpirationDate() {
    return LocalDateTime.now()
          .plusDays(7)
          .toInstant(ZoneOffset.of("-03:00"));
  }

  /** Validates a given token. */
  public String validateToken(String token) {
    Algorithm algorithm = Algorithm.HMAC256(secret);
    return JWT.require(algorithm)
      .withIssuer("HarvestHub")
      .build()
      .verify(token)
      .getSubject();
  }
}