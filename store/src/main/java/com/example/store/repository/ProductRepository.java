package com.example.store.repository;

import com.example.store.collection.Product;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    public List<Product> findByName(String name);

    @Query( "{'name' : { $regex: ?0, $options: 'i'}}")
    public List<Product> findByNameRegex(String name);

    public List<Product> findByCompanyNameIgnoreCase(String companyName);

    public long countByCompanyNameIgnoreCase(String companyName);

    @Aggregation(
            pipeline = "{ '$match': { 'company.name':  '?0' } }"
    )
    public List<Product> findByCompanyNameAggregate(String companyName);

    @Aggregation(
            pipeline = {
                    "{ $match : {'company.name': ?0 } }",
                    "{ $count: 'count' }"
            }
    )
    public long countByCompanyNameAggregation(String companyName);

}
