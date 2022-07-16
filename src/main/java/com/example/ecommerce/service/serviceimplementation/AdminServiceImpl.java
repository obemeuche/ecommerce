package com.example.ecommerce.service.serviceimplementation;

import com.example.ecommerce.exception.CustomAppException;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final ProductRepository productRepository;


    @Override
    public Product addProduct(@ModelAttribute Product productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .category(productDto.getCategory())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .build();
        return productRepository.save(product);
    }


    @Override
    public Product updateProduct(Long id) {
        Product product1 = productRepository.findById(id)

                .orElseThrow(()-> new CustomAppException("Product doesn't exit."));
        Product newPro = new Product();
        newPro.setId(product1.getId());
        newPro.setName(product1.getName());
        newPro.setCategory(product1.getCategory());
        newPro.setPrice(product1.getPrice());

        return productRepository.save(newPro);
    }


    @Override
    public Customer viewAllCustomers() {
        return null;
    }

    @Override
    public List<Product> getAllProducts () {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
