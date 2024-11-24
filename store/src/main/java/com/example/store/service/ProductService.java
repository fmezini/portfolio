package com.example.store.service;


import com.example.store.collection.Product;
import com.example.store.dto.ProductDTO;
import com.example.store.exception.StoreApplicationException;
import com.example.store.repository.ProductDAO;
import com.example.store.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private ProductDAO dao;

    @Autowired
    private ModelMapper mapper;

    public ProductDTO add(ProductDTO productDto) {
        if (productDto == null) {
            throw new StoreApplicationException();
        }
        Product product = repository.save(mapper.map(productDto, Product.class));
        return mapper.map(product, ProductDTO.class);
    }

    public ProductDTO update(String id, ProductDTO productDto) {
        Product product = repository.save(mapper.map(productDto, Product.class));
        return mapper.map(product, ProductDTO.class);

    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public ProductDTO findById(String id) {
        return mapper.map(repository.findById(id), ProductDTO.class);
    }

    public List<ProductDTO> findByName(String name) {
        return repository.findByName(name)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findByNameRegex(String name) {
        return repository.findByNameRegex(name)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public List<ProductDTO> findByCompanyName(String name) {
        return repository.findByCompanyNameIgnoreCase(name)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public long countByCompanyName(String name) {
        return repository.countByCompanyNameIgnoreCase(name);
    }

    public List<ProductDTO> findByCompanyNameAggregate(String name) {
        return repository.findByCompanyNameAggregate(name)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }

    public long countByCompanyNameAggregate(String name) {
        return repository.countByCompanyNameAggregation(name);
    }

    public List<ProductDTO> findByNameCompanyName(String name, String companyName) {
        return dao.findByNameAndCompanyName(name, companyName)
                .stream()
                .map(p -> mapper.map(p, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
