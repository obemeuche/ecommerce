package com.example.ecommerce.repository;

import com.example.ecommerce.models.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}
