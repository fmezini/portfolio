package com.example.bdd.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    @CollectionTable(
            name="phone",
            joinColumns=@JoinColumn(name="author_id")
    )
    @Column(name="phone")
    private List<String> phones;

    // cascade was added only to make easier to store data.
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> bookList;
}
