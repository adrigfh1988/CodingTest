package com.example.codingtest.infrastructure.persistence.repository;

import com.example.codingtest.domain.repository.PriceRepository;
import com.example.codingtest.domain.service.PriceForProductAndBrand;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@Import(PriceRepositoryH2.class)
//@Sql({"/sql/data.sql"})
class PriceRepositoryH2Test {

    @Autowired
    PriceRepository priceRepository;

    @Test
    @DisplayName("test to validate the correct behavior of our repository layer")
    void validateCorrectValueWithAmbiguityTest() {

        Optional<PriceForProductAndBrand> priceForProduct = priceRepository.getPriceForProduct(
                35455L,
                1L,
                LocalDateTime.of(2020, 6, 14, 16, 0));

        PriceForProductAndBrand expectedResult = PriceForProductAndBrand.builder()
                .productId(35455L)
                .brandId(1L)
                .appliedFee(2L)
                .startDate(LocalDateTime.of(2020, 6, 14, 15, 0))
                .endDate(LocalDateTime.of(2020, 6, 14, 18, 30))
                .finalPriceToApply(BigDecimal.valueOf(25.45))
                .build();

        assertThat(priceForProduct).isPresent().hasValue(expectedResult);


    }

    @Test
    @DisplayName("Validate Empty Result")
    void validateReturnsNullIfNoValueExists() {

        Optional<PriceForProductAndBrand> priceForProduct = priceRepository.getPriceForProduct(
                2L,
                1L,
                LocalDateTime.of(2020, 6, 14, 16, 0));

        assertThat(priceForProduct).isNotPresent();


    }

}