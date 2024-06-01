package com.example.spring_data.dto;

import lombok.Data;

import java.util.List;

@Data
public class AuthorDTO {

    private Long id;

    private String name;

    private List<String> phones;

    private List<BookDTO> bookList;
}
