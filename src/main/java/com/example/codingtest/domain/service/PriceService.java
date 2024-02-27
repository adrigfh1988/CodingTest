package com.example.codingtest.domain.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceService {
    Optional<PriceForProductAndBrand> retrievePriceAndFee(Long productId, Long brandId, LocalDateTime dateApplied);
}
