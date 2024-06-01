package com.example.spring_data.rest;

import com.example.spring_data.dto.AuthorDTO;
import com.example.spring_data.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(AuthorRest.class)
public class AuthorRestTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService service;

    AuthorDTO authorDTO;

    @BeforeEach
    public void setUp() {
        authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Test user");
        when(service.findById(1L)).thenReturn(authorDTO);
    }

    @Test
    public void testFindById() throws Exception {

        MvcResult result = mockMvc.perform(get("/author/id/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());
        String expected = "{\"id\":1,\"name\":\"Test user\",\"phones\":null,\"bookList\":null}";

        assertThat(expected).isEqualTo(result.getResponse().getContentAsString());

    }


}
