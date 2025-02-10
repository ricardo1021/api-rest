package com.apirest.exercise.controller;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.exception.BadRequestException;
import com.apirest.exercise.exception.BusinessException;
import com.apirest.exercise.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Product API", description = "Endpoints for managing products")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Filter products by price range", description = "Returns products within the specified price range")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "204", description = "No products found within the specified range"),
            @ApiResponse(responseCode = "400", description = "Invalid price range provided")
    })
    @GetMapping("/filter/price/{initialRange}/{finalRange}")
    public ResponseEntity<List<ProductDTO>> filterByPrice(@PathVariable int initialRange, @Valid @PathVariable int finalRange) {
        List<ProductDTO> filteredProducts = productService.getProductsByPriceRange(initialRange, finalRange);
        if (initialRange < 0 || finalRange < 0 || initialRange > finalRange) {
            throw new BadRequestException("El rango de precio es inválido.");
        }
        if (filteredProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filteredProducts);
    }

    @Operation(summary = "Sort products by price", description = "Returns product names sorted from lowest to highest price")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products sorted successfully")
    })
    @GetMapping("/sort/price")
    public ResponseEntity<List<String>> sortByPrice() {
        return ResponseEntity.ok(productService.getProductsByPriceSort());
    }
}
