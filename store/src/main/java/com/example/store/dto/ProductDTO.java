package com.example.store.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String id;

    private String name;

    private String description;

    private CompanyDTO company;

}
