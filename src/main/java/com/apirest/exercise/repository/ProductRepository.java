package com.apirest.exercise.repository;

import com.apirest.exercise.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, String> {
    List<Product> findByPriceBetween(int initialRange, int finalRange);
}
