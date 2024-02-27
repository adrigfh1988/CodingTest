package com.example.codingtest.application.mapper;

import com.example.codingtest.application.rest.response.PriceResponse;
import com.example.codingtest.domain.service.PriceForProductAndBrand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ResponseMapper {

    ResponseMapper INSTANCE = Mappers.getMapper( ResponseMapper.class );
    PriceResponse sourceToDestination(PriceForProductAndBrand source);

}
