package com.example.store.repository;

import com.example.store.collection.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Product insert(Product product) {
        return mongoTemplate.insert(product);
    }

    public List<Product> findByNameAndCompanyName(String name, String companyName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").is(name));
        query.addCriteria(Criteria.where("company.name").is(companyName));
        return mongoTemplate.find(query, Product.class);
    }

}
