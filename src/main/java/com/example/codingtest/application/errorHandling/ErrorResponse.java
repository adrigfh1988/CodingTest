package com.example.codingtest.application.errorHandling;

import lombok.Builder;

import java.util.List;

@Builder
public record ErrorResponse(List<String> errors) {
}
