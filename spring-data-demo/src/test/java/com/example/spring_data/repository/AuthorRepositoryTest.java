package com.example.spring_data.repository;

import com.example.spring_data.entity.Author;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private EntityManager entityManager;

    private Author testAuthor;

    @BeforeEach
    public void setUp() {
        testAuthor = new Author();
        testAuthor.setName("Test name");

        entityManager.persist(testAuthor);
        entityManager.flush();
    }

    @Test
    public void persistRightObject() {
        List<Author> list = repository.findByName("Test name");
        list.stream().forEach(p2 -> assertThat(testAuthor.getName()).isEqualTo(p2.getName()));
    }

    @Test
    public void notFound() {
        List<Author> list = repository.findByName("Abcd");
        assertThat(list).isEmpty();
    }

}
