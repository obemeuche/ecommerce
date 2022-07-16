package com.example.ecommerce.repository;

import com.example.ecommerce.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmailAndPassword(String email, String password);

}
