package com.apirest.exercise.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String barcode;
    private String item;
    private String category;
    private Integer price;
    private Integer discount;
    private Integer available;
}