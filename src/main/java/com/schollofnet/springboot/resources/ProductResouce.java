package com.schollofnet.springboot.resources;

import com.schollofnet.springboot.models.Product;
import com.schollofnet.springboot.services.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Api(value = "API Rest - Model Product")
@RestController
@RequestMapping("/products")
public class ProductResouce {

    @Autowired
    private ProductService productService;

    public ProductResouce(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "Find all products in database")
    @GetMapping(produces = "application/json")
    @ResponseBody
    public  ResponseEntity<?> findAll() {
        List<Product> productList = this.productService.findAll();
        return new ResponseEntity<List>(productList, HttpStatus.OK);
    }

    @ApiOperation(value = "Find by id in database")
    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?>  findById(@PathVariable(value = "id") Long id) {
        Optional<Product> product = (this.productService.find(id));
        return ResponseEntity.ok(product);
    }


    @ApiOperation(value = "Create a new Product")
    @PostMapping
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CREATED)
    public ResponseEntity<?> create(@Valid @RequestBody Product product, Errors errors) {
        if (!errors.hasErrors()) {
            Product productCreated = this.productService.create(product);
            return new ResponseEntity<Product>(productCreated, HttpStatus.CREATED);
        }

        String error = errors.getAllErrors()
                .stream()
                .map(msg -> msg.getDefaultMessage())
                .collect(Collectors.joining(","));

        return ResponseEntity.badRequest().body(error);

    }


    @ApiOperation(value = "Update a Product by id")
    @PutMapping(value = "/{id}")
    @ResponseBody
    private  ResponseEntity<?>  update(@PathVariable(value = "id") Long id, @Valid @RequestBody Product product, Errors errors) {
        if (!errors.hasErrors()) {
            Product productUpdated = this.productService.update(id, product);
            return new ResponseEntity<Product>(productUpdated, HttpStatus.CREATED);
        }

        String error = errors.getAllErrors()
                .stream()
                .map(msg -> msg.getDefaultMessage())
                .collect(Collectors.joining(","));

        return ResponseEntity.badRequest().body(error);
    }

    @ApiOperation(value = "Delete a Product by id")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        this.productService.delete(id);
    }
}
