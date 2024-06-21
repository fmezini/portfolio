package com.example.graphql.repository;

import com.example.graphql.entity.Author;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    public List<Author> findByName(String name);

    @Query(value = "select p from Author p where p.name = :name")
    public List<Author> findUsingJPQL(String name);


    @Query(value = "select * from Person where name = :name", nativeQuery = true)
    public List<Author> findUsingNativeQuery(String name);

}
