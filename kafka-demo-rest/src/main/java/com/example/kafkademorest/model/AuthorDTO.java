package com.example.kafkademorest.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {

    private Long id;

    private String name;

    private List<String> phones;
}
