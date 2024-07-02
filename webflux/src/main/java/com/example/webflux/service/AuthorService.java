package com.example.webflux.service;

import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.repository.AuthorRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
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


    public Flux<Author> findAll() {
        return repository.findAll();
    }

    public Flux<Author> findAll(Author author) {
        return repository.findAll(Example.of(author));
    }
    public Flux<AuthorDTO> findAllDynamic(Author author) {
        return DatabaseClient.create(factory)
                .sql("SELECT id, name FROM AUTHOR where id = :id and name = :name")
                .bind("id", author.getId())
                .bind("name", author.getName())
                .map(row -> {
                    AuthorDTO dto = new AuthorDTO();
                    dto.setId(row.get("id", Integer.class));
                    dto.setName(row.get("name", String.class));
                    return dto;
        } ).all();
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
