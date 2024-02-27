package com.example.codingtest.domain.service;

import com.example.codingtest.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DomainPriceService implements PriceService {

    private final PriceRepository priceRepository;
    @Override
    public Optional<PriceForProductAndBrand> retrievePriceAndFee(Long productId, Long brandId, LocalDateTime dateApplied) {
        return priceRepository.getPriceForProduct(productId,brandId, dateApplied);
    }
}
