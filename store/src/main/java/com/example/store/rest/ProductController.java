package com.example.store.rest;


import com.example.store.dto.ProductDTO;
import com.example.store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO product) {
        return ResponseEntity.ok(productService.add(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable("id") String id, @RequestBody ProductDTO product) {
        return ResponseEntity.ok(productService.update(id, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable("id") String id) {
        productService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") String id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByName(name));
    }

    @GetMapping("/name-regex/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByNameRegex(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByNameRegex(name));
    }

    @GetMapping("/company-name/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByCompanyName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByCompanyName(name));
    }

    @GetMapping("/company-name-count/{name}")
    public ResponseEntity<Long> getCountProductByCompanyName(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.countByCompanyName(name));
    }

    @GetMapping("/company-name-aggregate/{name}")
    public ResponseEntity<List<ProductDTO>> getProductByCompanyNameAggregate(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.findByCompanyNameAggregate(name));
    }

    @GetMapping("/company-name-aggregate-count/{name}")
    public ResponseEntity<Long> getProductByCompanyNameAggregateCount(@PathVariable("name") String name) {
        return ResponseEntity.ok(productService.countByCompanyNameAggregate(name));
    }

    @GetMapping("/name-company-name-aggregate-count/{name}/{companyName}")
    public ResponseEntity<List<ProductDTO>> getProductByCompanyNameAggregateCount(@PathVariable("name") String name, @PathVariable("companyName") String companyName) {
        return ResponseEntity.ok(productService.findByNameCompanyName(name, companyName));
    }
}
