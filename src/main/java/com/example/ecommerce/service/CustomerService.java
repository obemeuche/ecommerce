package com.example.ecommerce.service;

import com.example.ecommerce.dto.CustomerResponseDto;
import com.example.ecommerce.dto.CustomerSignUpDto;
import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.WishList;

import java.util.List;

public interface CustomerService {

    Customer createCustomer (Customer customerSignUp);
    Customer login (String email, String password);

    List<CartItem> getAllCartItem ();

    CartItem addToCart(Long id);
}
