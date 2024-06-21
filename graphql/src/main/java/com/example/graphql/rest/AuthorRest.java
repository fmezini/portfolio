package com.example.graphql.rest;

import com.example.graphql.dto.AuthorDTO;
import com.example.graphql.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class AuthorRest {

    private final AuthorService service;

    @QueryMapping
    public AuthorDTO findPersonById(@Argument("id") Long id) {
        return service.findById(id);
    }

    @MutationMapping
    public AuthorDTO addAuthor(@Argument AuthorDTO author) {
        return service.addAuthor(author);
    }
}
