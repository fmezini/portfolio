package com.example.webflux.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "author")
public class Author {


    @Id
    private Integer id;

    private String name;

}
