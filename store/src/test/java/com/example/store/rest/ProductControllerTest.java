package com.example.store.rest;

import com.example.store.dto.ProductDTO;
import com.example.store.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ProductService service;


    @Test
    public void addProduct_happy_path() throws Exception {
        ProductDTO productDTO = productDTO("1");
        when(service.add(any())).thenReturn(productDTO);

        mockMvc.perform(
                post("/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dtoToJson(productDTO))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(dtoToJson(productDTO)));
    }



    private ProductDTO productDTO(String id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        return dto;
    }

    private String dtoToJson(ProductDTO productDTO) throws JsonProcessingException {
        return mapper.writeValueAsString(productDTO);
    }

}
