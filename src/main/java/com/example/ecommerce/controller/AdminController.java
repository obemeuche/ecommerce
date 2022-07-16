package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ProductDto;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;


    @GetMapping("/addProduct")
    public String addProductHome (Model model){
        model.addAttribute("productDTO", new ProductDto());
        return "addProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct (@ModelAttribute Product product, Model model){
        Product newProduct = adminService.addProduct(product);
        model.addAttribute("product", newProduct);

        if (Objects.nonNull(newProduct)) {
            return "redirect:/product";
        }
        return "return:/addProduct";
    }

    @GetMapping("/product")
    public String  home(Model model) {
        List<Product> listOfProducts = adminService.getAllProducts();
        model.addAttribute("listOfProducts", listOfProducts);
        return "product";
    }

    @PutMapping("updateProduct/update/{id}")
    public String updateProduct (@RequestParam("id") Long id, @RequestBody Model model){
        Product updateProduct = adminService.updateProduct(id);
        model.addAttribute("productDTO", updateProduct);

            return "addProduct";
    }

    @DeleteMapping("product/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        adminService.deleteProduct(id);
        return "redirect:/product";
    }
}
