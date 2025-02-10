package com.apirest.exercise.mapper;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDomain(Product product);
    Product toEntity(ProductDTO productDTO);
}