package com.schollofnet.springboot.services;

import com.schollofnet.springboot.models.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public List<Product> findAll();
    public Optional<Product> find(Long id);
    public Product create(Product product);
    public Product update(Long id, Product product);
    void delete(Long id);

}
