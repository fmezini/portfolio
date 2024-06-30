package com.example.webflux.service;

import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    public Flux<Author> findAll() {
        return repository.findAll();
    }

    public Mono<Author> findById(Integer id) {
        return repository.findById(id);
    }

    public Mono<Author> addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return repository.save(author);
    }

}
