package com.example.codingtest.application.rest;

import com.example.codingtest.domain.service.PriceForProductAndBrand;
import com.example.codingtest.domain.service.PriceService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PricesController.class)
class PricesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PriceService priceService;

    @Test
    @DisplayName("Validate Correct Mapping and Happy Path")
    void correctMappingAndValidResponse() throws Exception {

        PriceForProductAndBrand priceForProductAndBrand = PriceForProductAndBrand.builder()
                .productId(35L)
                .brandId(1L)
                .finalPriceToApply(BigDecimal.valueOf(500F))
                .startDate( LocalDateTime.of(2024, 12, 15, 20, 6))
                .endDate( LocalDateTime.of(2025, 11, 15, 20, 6))
                .appliedFee(28L)
                .build();


        when(priceService.retrievePriceAndFee(35L, 1L, LocalDateTime.of(2024, 12, 15, 20, 6))).thenReturn(Optional.of(priceForProductAndBrand));
        this.mockMvc.perform(get("/prices?product-id=35&brand-id=1&date-applied=2024-12-15 20:06:00"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                                {
                                  "productId": 35,
                                  "brandId": 1,
                                  "appliedFee": 28.0,
                                  "startDate":"2024-12-15 20:06:00",
                                  "endDate":"2025-11-15 20:06:00",
                                  "finalPriceToApply": 500.0
                                }
                        """));
    }

    @Test
    @DisplayName("No Product Found for Input, In this case the API wil return a 404")
    void noProductFoundForInput() throws Exception {

        when(priceService.retrievePriceAndFee(35L, 1L,  LocalDateTime.of(2024, 12, 15, 20, 6))).thenReturn(Optional.empty());
        this.mockMvc.perform(get("/prices?product-id=35&brand-id=1&date-applied=2024-12-15 20:06:00"))
                .andExpect(status().isNotFound());

    }

    @Test
    @DisplayName("verify the correct validation of the input for product ID")
    void productIdValidation() throws Exception {


        this.mockMvc.perform(get("/prices?product-id=aa&brand-id=1&date-applied=2024-12-15 20:06:00"))
                .andExpect(status().is4xxClientError()).andDo(print());

    }

    @Test
    @DisplayName("verify the correct validation of the input for product ID when using invalid number")
    void productIdValidationForInvalidNumber() throws Exception {


        this.mockMvc.perform(get("/prices?product-id=-15&brand-id=1&date-applied=2024-12-15 20:06:00"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                                {
                                  "errors": ["Product ID cannot be lower than 1"]
                                }
                        """));

    }

    @Test
    @DisplayName("verify the correct validation of the input for product ID  and brand Id and Date when using invalid data")
    void productIdAndBrandIdAndDateValidationForInvalidData() throws Exception {


        this.mockMvc.perform(get("/prices?product-id=-15&brand-id=-1&date-applied=2024-12-15 20:06:00"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("""
                                {
                                  "errors": ["Brand ID cannot be lower than 1","Product ID cannot be lower than 1"]
                                }
                        """));

    }

}