package com.daklan.thymeleafcourse.controllers;

import com.daklan.thymeleafcourse.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/product")
    public String getProduct() {

//        return "courses/introduction-to-spring";
        return "redirect:/index";
    }

    @RequestMapping("/courses/product/{id}")
    public String getProductById(@PathVariable Integer id, Model model) {

        model.addAttribute("product", productService.getProduct(id));

        return "/courses/product";
    }
}
