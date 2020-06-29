package com.geekbrains.work15.controllers;


import com.geekbrains.work15.entities.Product;
import com.geekbrains.work15.entities.Selection;
import com.geekbrains.work15.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private ProductsService productsService;

    @Autowired
    public void setProductsService(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping
    public String showProductsList(Model model, @ModelAttribute(value = "selection") Selection selection,
                                   @RequestParam(name = "page", required = false) Integer page) {
        if (page == null)
            page = 0;
        List<Product> content = productsService.getAllProducts(selection, page).getContent();
        model.addAttribute("products", content);
        model.addAttribute("selection", selection);
        return "products";
    }
}
