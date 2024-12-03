package com.example.webflux.service;


import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.repository.AuthorRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringJUnitConfig({ AuthorService.class, ModelMapper.class })
public class AuthorServiceTest {

    @Autowired
    private AuthorService service;

    @MockBean
    private AuthorRepository repository;

    @MockBean
    private ConnectionFactory factory;



    @Test
    public void test_findAll_happy_path() {

        when(repository.findAll()).thenReturn(Flux.just(author(1), author(2)));

        StepVerifier.create(service.findAll())
                .expectNext(authorDTO(1))
                .expectNext(authorDTO(2))
                .verifyComplete();
    }

    @Test
    public void test_findAll_empty() {
        when(repository.findAll()).thenReturn(Flux.just());

        StepVerifier.create(service.findAll())
                .verifyComplete();
    }

    @Test
    public void test_addAuthor_happy_path() {
        when(repository.save(any())).thenReturn(Mono.just(author(1)));
        StepVerifier.create(service.addAuthor(authorDTO(1)))
                .expectNext(authorDTO(1))
                .verifyComplete();
    }

    @Test
    public void test_findById_happy_path() {
        when(repository.findById(1)).thenReturn(Mono.just(author(1)));
        StepVerifier.create(service.findById(1))
                .expectNext(authorDTO(1))
                .verifyComplete();
    }

    private Author author(Integer id) {
        Author author = new Author();
        author.setId(id);
        author.setName("Random name");
        return author;
    }

    private AuthorDTO authorDTO(Integer id) {
        AuthorDTO author = new AuthorDTO();
        author.setId(id);
        author.setName("Random name");
        return author;
    }

}
