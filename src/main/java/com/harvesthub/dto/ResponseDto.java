package com.harvesthub.dto;

/** Response Data Tranfer Object. */
public record ResponseDto<T>(String message, T data) {
}