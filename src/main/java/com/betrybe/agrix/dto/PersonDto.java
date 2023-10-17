package com.betrybe.agrix.dto;

import com.betrybe.agrix.security.Role;

/** Data transfer object for Person. */
public record PersonDto(Long id, String username, Role role) {
}