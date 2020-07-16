package com.schollofnet.springboot.services;

import com.schollofnet.springboot.models.Product;
import com.schollofnet.springboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Product create(Product product) {
        this.productRepository.save(product);
        return product;
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> productUpdate = this.productRepository.findById(id);
        if (productUpdate != null) {
            product.setId(productUpdate.get().getId());
            this.productRepository.save(product);
            return product;
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Product> product = this.productRepository.findById(id);
        if (product != null) this.productRepository.deleteById(product.get().getId());
    }
}
