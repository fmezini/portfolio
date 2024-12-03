package com.example.webflux.controller;

import com.example.webflux.domain.Author;
import com.example.webflux.dto.AuthorDTO;
import com.example.webflux.service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.Mockito.when;

@WebFluxTest(controllers = AuthorController.class)
@Import({ModelMapper.class, ObjectMapper.class})
public class AuthorControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private AuthorService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void test_find_all_authors_happy_path() throws JsonProcessingException {
        AuthorDTO dto = authorDTO(1);
        AuthorDTO dto2 = authorDTO(2);

        when(service.findAll()).thenReturn(Flux.just(dto, dto2));

        webTestClient.get()
                .uri("/author")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo(objectMapper.writeValueAsString(List.of(dto, dto2)));
    }

    @Test
    public void test_find_all_authors_no_result() throws JsonProcessingException {
        when(service.findAll()).thenReturn(Flux.just());

        webTestClient.get()
                .uri("/author")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("[]");
    }

    @Test
    public void test_add_author_happy_path() throws JsonProcessingException {

        when(service.addAuthor(authorDTO(1))).thenReturn(Mono.just(authorDTO(1)));


        webTestClient.post()
                .uri("/author")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(authorDTO(1)), AuthorDTO.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(AuthorDTO.class)
                .isEqualTo(authorDTO(1));
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

