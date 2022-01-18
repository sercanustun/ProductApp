package com.bilgeadam.productapp.controller;

import com.bilgeadam.productapp.entity.Product;
import com.bilgeadam.productapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public List<Product> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") long id)
    {
        return new ResponseEntity<Product>(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.OK);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product product)
    {
        product.setId(id);
        return new ResponseEntity<Product>(productService.updateProduct(product), HttpStatus.OK);
    }


    @DeleteMapping("/product/{id}")
    public HttpStatus deleteProduct(@PathVariable("id") long id)
    {
        productService.deleteProduct(id);

        return HttpStatus.OK;
    }



}