package com.example.codingtest.application.rest;

import com.example.codingtest.application.mapper.ResponseMapper;
import com.example.codingtest.application.rest.response.PriceResponse;
import com.example.codingtest.domain.service.PriceService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
@Validated
public class PricesController {

    private final PriceService priceService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<PriceResponse> getPrices(

            @Parameter(
                    name = "product-id",
                    example = "35455",
                    required = true)
            @RequestParam("product-id") @Min(value = 1, message = "Product ID cannot be lower than 1") Long productId,

            @Parameter(
                    name = "brand-id",
                    example = "1",
                    required = true)
            @RequestParam("brand-id") @Min(value = 1, message = "Brand ID cannot be lower than 1") Long brandId,

            @RequestParam("date-applied")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME, fallbackPatterns = {"yyyy-MM-dd HH:mm:ss"})
            @Parameter(
                    name = "date-applied",
                    example = "2020-06-14 10:00:00",
                    required = true)
            LocalDateTime dateApplied) {


        Optional<PriceResponse> response = priceService.retrievePriceAndFee(productId, brandId, dateApplied)
                .map(ResponseMapper.INSTANCE::sourceToDestination);

        return response
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }
}
