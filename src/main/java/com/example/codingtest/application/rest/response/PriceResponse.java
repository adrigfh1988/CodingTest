package com.example.codingtest.application.rest.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceResponse(Long productId, Long brandId,
                            Long appliedFee,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                            LocalDateTime startDate,
                            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
                            LocalDateTime endDate,
                            BigDecimal finalPriceToApply) {
}
