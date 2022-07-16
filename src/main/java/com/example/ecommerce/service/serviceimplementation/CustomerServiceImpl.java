package com.example.ecommerce.service.serviceimplementation;

import com.example.ecommerce.exception.CustomAppException;
import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.models.WishList;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.WishListRepository;
import com.example.ecommerce.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ProductRepository productRepository;

    private CartRepository cartRepository;



    public static List<Product> cart;

    static {
        cart = new ArrayList<>();
    }

    public static List<Product> wishList;

    static {
        wishList = new ArrayList<>();
    }

    @Override
    public Customer createCustomer(Customer customerSignUp) {
        Customer customer = Customer.builder()
                .fullName(customerSignUp.getFullName())
                .email(customerSignUp.getEmail())
                .phoneNumber(customerSignUp.getPhoneNumber())
                .password(customerSignUp.getPassword())
                .build();
        return customerRepository.save(customer);
    }

    @Override
    public Customer login(String email, String password) {
        Customer customer = customerRepository.findByEmailAndPassword(email, password);
        return customer;
    }

    @Override
    public List<CartItem> getAllCartItem() {
        return cartRepository.findAll();
    }

    @Override
    public CartItem addToCart(Long id) {
        Product product1 = productRepository.findById(id)
                .orElseThrow(()-> new CustomAppException("Product not found!"));
        CartItem cartItem = new CartItem();
        cartItem.setProductName(product1.getName());
        cartItem.setCategory(product1.getCategory());
        cartItem.setPrice(product1.getPrice());
        return cartRepository.save(cartItem);
    }

}


