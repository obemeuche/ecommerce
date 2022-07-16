package com.example.ecommerce.service;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Product;

import java.util.List;
import java.util.Optional;

public interface AdminService {
    Product addProduct (Product productDto);

    public Product updateProduct(Long id);
    Customer viewAllCustomers();

     List<Product> getAllProducts ();


    void deleteProduct(Long id);

    Optional<Product> getProductById(Long id);


}
