package com.example.webflux.controller;

import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.service.AuthorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService service;

    private final ModelMapper modelMapper;



    @GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<AuthorDTO> getAuthors() {
        return service.findAll().map(a -> modelMapper.map(a, AuthorDTO.class));
    }

    @GetMapping(value="/author/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<AuthorDTO> getAuthor(@PathVariable("id") Integer id) {
        return service.findById(1).map(a -> modelMapper.map(a, AuthorDTO.class));
    }

    @PostMapping(value="/author")
    public Mono<AuthorDTO> addAuthor(@RequestBody AuthorDTO author) {
        return service.addAuthor(author).map(a -> modelMapper.map(a, AuthorDTO.class));
    }

}
