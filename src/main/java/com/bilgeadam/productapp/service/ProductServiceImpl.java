package com.bilgeadam.productapp.service;

import com.bilgeadam.productapp.entity.Product;
import com.bilgeadam.productapp.exception.ResourceNotFoundException;
import com.bilgeadam.productapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Product product) {

        Optional<Product> productDB = productRepository.findById(product.getId());

        if (productDB.isPresent())
        {
            Product productUpdate = productDB.get();
            productUpdate.setDescription(product.getDescription());
            productUpdate.setName(product.getName());
            productUpdate.setPrice(product.getPrice());
            productRepository.save(productUpdate);

            return productUpdate;
        }
        else
        {
            throw new ResourceNotFoundException("Record not found with id:" + product.getId());
        }

    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) {

        Optional<Product> productDB = productRepository.findById(id);

        if (productDB.isPresent())
        {
            return productDB.get();
        }
        else
        {
            throw new ResourceNotFoundException("Record not found with id:" + id);
        }

    }

    public void deleteProduct(long id) {
        Optional<Product> productDB = productRepository.findById(id);

        if (productDB.isPresent())
        {
            productRepository.delete(productDB.get());
        }
        else
        {
            throw new ResourceNotFoundException("Record not found with id:" + id);
        }
    }
}