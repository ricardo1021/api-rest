package com.apirest.exercise.controller;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.exception.BadRequestException;
import com.apirest.exercise.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.apirest.exercise.common.Constants.ZERO_VALUE;

@Tag(name = "Product API", description = "Endpoints for managing products")
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
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
        if (initialRange < ZERO_VALUE || finalRange < ZERO_VALUE || initialRange > finalRange) {
            log.error("Price range is invalid.");
            throw new BadRequestException("Price range is invalid.");
        }
        var filteredProducts = productService.getProductsByPriceRange(initialRange, finalRange);
        if (CollectionUtils.isEmpty(filteredProducts)) {
            log.info("Filtered list is empty.");
            return ResponseEntity.noContent().build();
        }
        log.info(String.format("Filtered list size = %s ", filteredProducts.size()));
        return ResponseEntity.ok(filteredProducts);
    }

    @Operation(summary = "Sort products by price", description = "Returns product names sorted from lowest to highest price")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Products sorted successfully")
    })
    @GetMapping("/sort/price")
    public ResponseEntity<List<String>> sortByPrice() {
        var productList = productService.getProductsByPriceSort();
        if (CollectionUtils.isEmpty(productList)) {
            log.info("Product list is empty.");
            return ResponseEntity.noContent().build();
        }
        log.info(String.format("Product list size = %s ", productList.size()));
        return ResponseEntity.ok(productList);
    }
}
