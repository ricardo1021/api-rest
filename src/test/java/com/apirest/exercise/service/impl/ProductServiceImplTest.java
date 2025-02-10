package com.apirest.exercise.service.impl;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.mapper.ProductMapper;
import com.apirest.exercise.model.Product;
import com.apirest.exercise.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProductsByPriceRange_ShouldReturnProductsWithinPriceRange() {
        Product product1 = new Product("0001", "Item1", "200", 10, 1, 1);
        Product product2 = new Product("0001", "Item2", "150", 10, 1, 1);
        List<Product> products = Arrays.asList(product1, product2);

        ProductDTO product3 = generateProduct("0001", "Item1", 200);
        ProductDTO product4 = generateProduct("0002", "Item2", 150);
        List<ProductDTO> productsDtos = Arrays.asList(product3, product4);

        when(productRepository.findByPriceBetween(100, 200)).thenReturn(products);
        when(productMapper.toDomain(product1)).thenReturn(product3);
        when(productMapper.toDomain(product2)).thenReturn(product4);

        List<ProductDTO> result = productService.getProductsByPriceRange(100, 200);

        assertEquals(2, result.size());
        assertEquals("Item1", result.get(0).getItem());
        assertEquals("Item2", result.get(1).getItem());
    }

    private ProductDTO generateProduct(String barcode, String name, int price) {
        return ProductDTO.builder()
                .barcode(barcode)
                .item(name)
                .price(price)
                .build();
    }

    @Test
    void getProductsByPriceSort_ShouldReturnProductsSortedByPrice() {
        Product product1 = new Product("0001", "Item1", "200", 50, 1, 1);
        Product product2 = new Product("0001", "Item2", "150", 10, 1, 1);
        List<Product> products = Arrays.asList(product1, product2);

        ProductDTO product3 = generateProduct("0001", "Item1", 200);
        ProductDTO product4 = generateProduct("0002", "Item2", 150);
        List<ProductDTO> productsDtos = Arrays.asList(product3, product4);

        when(productRepository.findAll()).thenReturn(products);

        List<String> result = productService.getProductsByPriceSort();

        assertEquals(2, result.size());
        assertEquals("Item2", result.get(0));
        assertEquals("Item1", result.get(1));
    }
}