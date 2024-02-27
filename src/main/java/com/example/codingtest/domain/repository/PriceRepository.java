package com.example.codingtest.domain.repository;

import com.example.codingtest.domain.service.PriceForProductAndBrand;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
    Optional<PriceForProductAndBrand> getPriceForProduct(Long productId, Long brandId, LocalDateTime dateApplied);
}
