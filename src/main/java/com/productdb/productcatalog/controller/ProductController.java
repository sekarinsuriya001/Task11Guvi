package com.productdb.productcatalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.productdb.productcatalog.model.Product;
import com.productdb.productcatalog.repository.ProductRepository;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/add-product")
    public String showAddProductForm(Model model){
        model.addAttribute("product",new Product());
        return "add-product-form";
    }

    @PostMapping("/add-product")
    public String saveProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/display-products";
    }

    @GetMapping("/display-products")
    public String displayProducts(Model model){
        List<Product>products=productRepository.findAll();
        model.addAttribute("products",products);
        return "/display-products";
    }
}
