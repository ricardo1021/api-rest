package com.apirest.exercise.service;

import com.apirest.exercise.domain.ProductDTO;

import java.util.List;

public interface ProductService {

    List<ProductDTO> getProductsByPriceRange(int initialRange, int finalRange);

    List<String> getProductsByPriceSort();
}
