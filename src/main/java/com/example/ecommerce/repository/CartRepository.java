package com.example.ecommerce.repository;

import com.example.ecommerce.models.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartItem, Long> {

}
