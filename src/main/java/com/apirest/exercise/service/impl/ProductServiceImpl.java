package com.apirest.exercise.service.impl;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.mapper.ProductMapper;
import com.apirest.exercise.repository.ProductRepository;
import com.apirest.exercise.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<ProductDTO> getProductsByPriceRange(int initialRange, int finalRange) {
        log.info("getProductsByPriceRange");
        return productRepository.findByPriceBetween(initialRange, finalRange)
                .stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public List<String> getProductsByPriceSort() {
        log.info("getProductsByPriceSort");
        return  productRepository.findAll()
                .stream()
                .map(ProductMapper.INSTANCE::toDomain)
                .sorted(Comparator.comparingInt(ProductDTO::getPrice))
                .map(ProductDTO::getItem)
                .toList();
    }
}
