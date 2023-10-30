package com.agrix.dto;

import com.agrix.security.Role;

/** Data transfer object for Person. */
public record PersonDto(Long id, String username, Role role) {}