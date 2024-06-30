package com.example.webflux.repository;

import com.example.webflux.domain.Author;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository  extends R2dbcRepository<Author, Integer> {

}
