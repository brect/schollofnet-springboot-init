package com.schollofnet.springboot.resources;

import com.schollofnet.springboot.models.Product;
import com.schollofnet.springboot.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductResouce {

    @Autowired
    private ProductService productService;

    public ProductResouce(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    @ResponseBody
    public List<Product> findAll() {
        return this. productService.findAll();
    }

    @GetMapping(value = "/{id}")
    @ResponseBody
    public Optional<Product> findById(@PathVariable(value = "id") Long id) {
        return this.productService.findById(id);
    }

    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return this.productService.create(product);
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    private Product update(@PathVariable(value = "id") Long id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        this.productService.delete(id);
    }
}
