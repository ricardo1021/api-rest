package com.apirest.exercise.controller;

import com.apirest.exercise.domain.ProductDTO;
import com.apirest.exercise.exception.BadRequestException;
import com.apirest.exercise.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    void filterByPrice_ShouldReturnFilteredProducts() throws Exception {
        ProductDTO product1 = generateProduct("0001", "Item1", 100);
        ProductDTO product2 = generateProduct("0001", "Item2", 150);
        List<ProductDTO> filteredProducts = Arrays.asList(product1, product2);
        when(productService.getProductsByPriceRange(100, 200)).thenReturn(filteredProducts);

        mockMvc.perform(get("/products/filter/price/100/200"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].item").value("Item1"))
                .andExpect(jsonPath("$[1].item").value("Item2"));

        verify(productService, times(1)).getProductsByPriceRange(100, 200);
    }

    @Test
    void filterByPrice_ShouldReturnNoContentWhenNoProductsFound() throws Exception {
        // Arrange
        when(productService.getProductsByPriceRange(100, 200)).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/products/filter/price/100/200"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).getProductsByPriceRange(100, 200);
    }

    @Test
    void filterByPrice_ShouldReturnBadRequestWhenPriceRangeIsInvalid() throws Exception {
        mockMvc.perform(get("/products/filter/price/-100/200"))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/products/filter/price/200/100"))
                .andExpect(status().isBadRequest());

        verify(productService, times(0)).getProductsByPriceRange(anyInt(), anyInt());
    }

    @Test
    void sortByPrice_ShouldReturnSortedProductNames() throws Exception {
        // Arrange
        ProductDTO product1 = generateProduct("0001", "Item1", 200);
        ProductDTO product2 = generateProduct("0001", "Item2", 100);
        List<String> sortedProductNames = Arrays.asList("Item2", "Item1");
        when(productService.getProductsByPriceSort()).thenReturn(sortedProductNames);

        // Act & Assert
        mockMvc.perform(get("/products/sort/price"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("Item2"))
                .andExpect(jsonPath("$[1]").value("Item1"));

        verify(productService, times(1)).getProductsByPriceSort();
    }

    @Test
    void sortByPrice_ShouldReturnNoContentWhenNoProductsFound() throws Exception {
        // Arrange
        when(productService.getProductsByPriceSort()).thenReturn(List.of());

        // Act & Assert
        mockMvc.perform(get("/products/sort/price"))
                .andExpect(status().isNoContent());

        verify(productService, times(1)).getProductsByPriceSort();
    }

    private ProductDTO generateProduct(String barcode, String name, int price) {
        return ProductDTO.builder()
                .barcode(barcode)
                .item(name)
                .price(price)
                .build();
    }
}