package com.example.bdd.service;

import com.example.bdd.dto.AuthorDTO;
import com.example.bdd.entity.Author;
import com.example.bdd.entity.Author_;
import com.example.bdd.exception.ClientNotFoundException;
import com.example.bdd.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    public final AuthorRepository repository;

    public final ModelMapper modelMapper;

    public AuthorDTO findById(Long id) {
        Author author = repository.findById(id).orElseThrow(ClientNotFoundException::new);
        return modelMapper.map(author, AuthorDTO.class);
    }

    public List<AuthorDTO> findPersonByName(String name) {
        return repository.findByName(name).stream().map(s -> modelMapper.map(s, AuthorDTO.class)).toList();
    }

    public List<AuthorDTO> findPersonByNameJPQL(String name) {
        return repository.findUsingJPQL(name).stream().map(s -> modelMapper.map(s, AuthorDTO.class)).toList();
    }

    public List<AuthorDTO> findPersonByNameNativeQuery(String name) {
        return repository.findUsingNativeQuery(name).stream().map(s -> modelMapper.map(s, AuthorDTO.class)).toList();
    }

    public List<AuthorDTO> findPersonByNameSpecification(String name) {
        return repository.findAll(
        (root, query, builder) -> builder.equal(root.get(Author_.name), name)
        ).stream().map(s -> modelMapper.map(s, AuthorDTO.class)).toList();
    }

    public AuthorDTO addAuthor(AuthorDTO author) {
        Author authorEntity = modelMapper.map(author, Author.class);
        Author authorSaved = repository.save(authorEntity);
        return modelMapper.map(authorSaved, AuthorDTO.class);
    }

}
