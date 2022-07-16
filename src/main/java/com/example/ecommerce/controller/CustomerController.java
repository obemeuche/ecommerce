package com.example.ecommerce.controller;

import com.example.ecommerce.models.CartItem;
import com.example.ecommerce.models.Customer;
import com.example.ecommerce.dto.CustomerSignUpDto;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.AdminService;
import com.example.ecommerce.service.CustomerService;
import com.example.ecommerce.service.serviceimplementation.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Objects;


@Controller
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final AdminService adminService;


    @GetMapping({"/"})
    public String home(Model model){
        return "index";
    }

    @GetMapping("/signup")
    public ModelAndView signup() {
        ModelAndView mav = new ModelAndView("signup");
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("customer", new Customer());
        return mav;
    }

    @GetMapping("/adminHome")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/shop")
    public String shop(Model model){
        List<Product> listOfProducts = adminService.getAllProducts();
        model.addAttribute("listOfProducts", listOfProducts);
        return "shop";
    }

    @PostMapping("/signup")
    public String createAccount(@ModelAttribute Customer customer, Model model) {

        Customer newCustomer = customerService.createCustomer(customer);
        model.addAttribute("customer", newCustomer);

        if(Objects.nonNull(newCustomer)) {
            return "redirect:/login";
        } else {
            return "redirect:/signup";
        }

    }

    @PostMapping("/login")
    public String login(@ModelAttribute Customer customer, Model model ) {

        Customer oauthUser = customerService.login(customer.getEmail(), customer.getPassword());
        if(oauthUser != null) {
            model.addAttribute("customer", oauthUser);
            if (oauthUser.getEmail().equals("admin@gmail.com") && oauthUser.getPassword().equals("admin")) {
                return "adminHome";
            }
            else {
                return "redirect:/shop";
            }

        } else {
            return "redirect:/login";
        }

    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cartCount", CustomerServiceImpl.cart.size());
        model.addAttribute("total",CustomerServiceImpl.cart.stream().mapToInt(Product::getPrice).sum());
        model.addAttribute("cart", CustomerServiceImpl.cart);

        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart (@PathVariable long id) {
        CustomerServiceImpl.cart.add(adminService.getProductById(id).get());

        return "redirect:/cart";
    }

    @GetMapping("/wishList")
    public String wisList(Model model) {
        model.addAttribute("cartCount", CustomerServiceImpl.wishList.size());
        model.addAttribute("cart", CustomerServiceImpl.wishList);
        return "wishList";
    }

    @GetMapping("/wishList/{id}")
    public String addToWishList (Product product, @PathVariable long id) {
        CustomerServiceImpl.wishList.add(adminService.getProductById(id).get());

        return "redirect:/wishList";
    }


}
