package com.example.store.service;

import com.example.store.collection.Product;
import com.example.store.dto.ProductDTO;
import com.example.store.exception.StoreApplicationException;
import com.example.store.repository.ProductDAO;
import com.example.store.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringJUnitConfig(classes = {ProductService.class, ModelMapper.class})

public class ProductServiceTest {

    @Autowired
    private ProductService service;

    @MockBean
    private ProductRepository repository;

    @MockBean
    private ProductDAO dao;


    @Test
    public void add_test_happy_path() {
        // mocking the repository
        when(repository.save(product("1"))).thenReturn(product("1"));
        assertEquals(service.add(productDTO("1")), productDTO("1"));
    }

    @Test()
    public void add_test_null_object() {
        assertThrows(StoreApplicationException.class, () -> service.add(null));
    }


    private ProductDTO productDTO(String id) {
        ProductDTO dto = new ProductDTO();
        dto.setId(id);
        return dto;
    }


    private Product product(String id) {
        Product product = new Product();
        product.setId(id);
        return product;
    }

}
