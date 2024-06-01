package com.example.spring_data.rest;

import com.example.spring_data.dto.AuthorDTO;
import com.example.spring_data.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/author")
public class AuthorRest {

    private final AuthorService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<AuthorDTO> findPersonById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/query")
    public ResponseEntity<List<AuthorDTO>> findPersonByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.findPersonByName(name));
    }

    @GetMapping("/jpql/query")
    public ResponseEntity<List<AuthorDTO>> findPersonByNameJPQL(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.findPersonByNameJPQL(name));
    }

    @GetMapping("/native/query")
    public ResponseEntity<List<AuthorDTO>> findPersonByNameNativeQuery(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.findPersonByNameNativeQuery(name));
    }

    @GetMapping("/specification/query")
    public ResponseEntity<List<AuthorDTO>> findPersonByNameSpecification(@RequestParam("name") String name) {
        return ResponseEntity.ok(service.findPersonByNameSpecification(name));
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO author) {
        return ResponseEntity.ok(service.addAuthor(author));
    }
}
