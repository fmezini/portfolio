package com.example.spring_data.service;

import com.example.spring_data.dto.AuthorDTO;
import com.example.spring_data.entity.Author;
import com.example.spring_data.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorServiceTest {


    @MockBean
    private AuthorRepository repository;

    @Autowired
    private AuthorService service;

    @Autowired
    private ModelMapper mapper;

    private Author author;
    private AuthorDTO authorDTO;

    @BeforeEach
    public void setUp() {
        author = new Author();
        author.setName("Test name");
        authorDTO = mapper.map(author, AuthorDTO.class);

        //
        when(repository.findById(any())).thenReturn(Optional.empty());
        when(repository.findById(1L)).thenReturn(Optional.of(author));

        //
        List<Author> list = new ArrayList<>();
        when(repository.findByName(any())).thenReturn(list);
        when(repository.findByName("Test name")).thenReturn(list);

        when(repository.findUsingJPQL(any())).thenReturn(list);
        when(repository.findUsingJPQL("Test name")).thenReturn(list);

        when(repository.findUsingNativeQuery(any())).thenReturn(list);
        when(repository.findUsingNativeQuery("Test name")).thenReturn(list);


    }


    @Test
    public void testFindById() {
        AuthorDTO p2 = service.findById(1L);
        assertThat(p2).isEqualTo(authorDTO);
    }

    @Test
    public void testFindByIdEmpty() {
        assertThrows(RuntimeException.class, () -> service.findById(5L));
    }

    @Test
    public void testFindByName() {
        List<AuthorDTO> p2 = service.findPersonByName("Test name");
        p2.stream().forEach(
                p -> assertThat(authorDTO).isEqualTo(p)
        );
    }

    @Test
    public void testNotFindByName() {
        List<AuthorDTO> p2 = service.findPersonByName("Wrong name");
        assertThat(p2).isEmpty();
    }

    @Test
    public void testFindByNameJPQL() {
        List<AuthorDTO> p2 = service.findPersonByNameJPQL("Test name");
        p2.stream().forEach(
                p -> assertThat(authorDTO).isEqualTo(p)
        );
    }

    @Test
    public void testNotFindByNameJPQL() {
        List<AuthorDTO> p2 = service.findPersonByNameJPQL("Wrong name");
        assertThat(p2).isEmpty();
    }

    @Test
    public void testFindByNameNativeQuery() {
        List<AuthorDTO> p2 = service.findPersonByNameNativeQuery("Test name");
        p2.stream().forEach(
                p -> assertThat(authorDTO).isEqualTo(p)
        );
    }

    @Test
    public void testNotFindByNameNativeQuery() {
        List<AuthorDTO> p2 = service.findPersonByNameNativeQuery("Wrong name");
        assertThat(p2).isEmpty();
    }
}
