package com.example.codingtest.domain.service;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
public record PriceForProductAndBrand(Long productId,
                                      Long brandId,
                                      Long appliedFee,
                                      LocalDateTime startDate,
                                      LocalDateTime endDate,
                                      BigDecimal finalPriceToApply) {
}
