package com.apirest.exercise.repository;

import com.apirest.exercise.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    void testFindByPriceBetween() {
        Product p1 = new Product("123", "Item1", "Category1", 500, 10, 1);
        Product p2 = new Product("124", "Item2", "Category1", 1000, 15, 1);
        productRepository.saveAll(List.of(p1, p2));

        List<Product> products = productRepository.findByPriceBetween(400, 800);
        assertThat(products).hasSize(1);
        assertThat(products.get(0).getItem()).isEqualTo("Item1");
    }
}