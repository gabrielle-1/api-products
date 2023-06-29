package com.example.springboot.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ProductRecordDto(
        @NotBlank String name, @NotNull BigDecimal value, @NotNull LocalDateTime createdAt, @NotNull LocalDateTime updatedAt
) {

}
