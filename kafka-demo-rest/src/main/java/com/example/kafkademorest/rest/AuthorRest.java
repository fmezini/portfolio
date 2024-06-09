package com.example.kafkademorest.rest;

import lombok.RequiredArgsConstructor;
import com.example.kafkademorest.model.AuthorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.kafkademorest.service.AuthorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorRest {

    private final AuthorService service;

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO author) {
        return ResponseEntity.ok(service.addAuthor(author));
    }

}
