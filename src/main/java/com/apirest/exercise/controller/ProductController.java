package com.apirest.exercise.controller;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/filter/price/{initialRange}/{finalRange}")
    public ResponseEntity<List<ProductDTO>> filterByPrice(@PathVariable int initialRange, @PathVariable int finalRange) {
        List<ProductDTO> filteredProducts = productService.getProductsByPriceRange(initialRange, finalRange);
        if (filteredProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filteredProducts);
    }

    @GetMapping("/sort/price")
    public ResponseEntity<List<String>> sortByPrice() {
        return ResponseEntity.ok(productService.getProductsByPriceSort());
    }
}
