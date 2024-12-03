package com.example.webflux.service;

import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.repository.AuthorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository repository;

    private final ConnectionFactory factory;

    private final ModelMapper mapper;


    public Flux<AuthorDTO> findAll() {
        return repository.findAll().map(
                row -> mapper.map(row, AuthorDTO.class)
        );
    }

    public Flux<AuthorDTO> findAll(AuthorDTO author) {
        return repository.findAll(Example.of(mapper.map(author, Author.class))).map(row -> mapper.map(row, AuthorDTO.class));
    }

    public Flux<AuthorDTO> findAllDynamic(AuthorDTO author) {
        return DatabaseClient.create(factory)
                .sql("SELECT id, name FROM AUTHOR where id = :id and name = :name")
                .bind("id", author.getId())
                .bind("name", author.getName())
                .map(row -> mapper.map(row, AuthorDTO.class)).all();
    }

    public Mono<AuthorDTO> findById(Integer id) {
        return repository.findById(id).map(row -> mapper.map(row, AuthorDTO.class));
    }

    public Mono<AuthorDTO> addAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return repository.save(author).map(
                row -> mapper.map(row, AuthorDTO.class)
        );
    }

}
