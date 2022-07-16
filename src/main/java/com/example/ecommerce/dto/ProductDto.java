package com.example.ecommerce.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private String category;
    private Integer price;
    private String description;
    private String imgName;
}
