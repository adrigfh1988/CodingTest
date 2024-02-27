package com.example.codingtest.infrastructure.persistence.repository;

import com.example.codingtest.domain.repository.PriceRepository;
import com.example.codingtest.domain.service.PriceForProductAndBrand;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class PriceRepositoryH2 implements PriceRepository {

    //BRAND NEW JdbcClient:  Available Since Spring boot 3.2 https://spring.io/blog/2023/11/23/spring-boot-3-2-0-available-now
    private final JdbcClient jdbcClient;

    @Override
    public Optional<PriceForProductAndBrand> getPriceForProduct(Long productId, Long brandId, LocalDateTime dateApplied) {

        return jdbcClient.sql("""
                        SELECT TOP 1
                        PRODUCT_ID as productId,
                        BRAND_ID as brandId,
                        PRICE as finalPriceToApply,
                        PRICE_LIST as appliedFee,
                        START_DATE as startDate,
                        END_DATE as endDate,
                        FROM PRICES
                        WHERE PRODUCT_ID = :productId AND
                              BRAND_ID = :brandId AND
                             :dateApplied >= START_DATE AND :dateApplied <= END_DATE
                        ORDER BY PRIORITY DESC
                        """
        ).param("productId",productId)
                .param("brandId",brandId)
                .param("dateApplied",dateApplied)
                .query(PriceForProductAndBrand.class)
                .optional();
    }
}
