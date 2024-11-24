package com.example.store.repository;

import com.example.store.collection.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataMongoTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository repository;

    @Test
    public void save_test_happy_path() {

        Product product = product(null);
        assertEquals(repository.save(product), product);
        System.out.println(product.getId());
    }

    private Product product(String id) {
        Product product = new Product();
        product.setId(id);
        product.setName("test asaas wwww");
        return product;
    }

}
