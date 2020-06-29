package com.geekbrains.work15.services;


import com.geekbrains.work15.entities.Product;
import com.geekbrains.work15.entities.Selection;
import com.geekbrains.work15.repositories.ProductRepository;
import com.geekbrains.work15.specifications.ProductsSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductsService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(Selection selection, int page) {
        Specification<Product> spec = Specification.where(null);
        if (selection != null) {
            if (!selection.getTitlesPart().equals("")) {
                spec = spec.and(ProductsSpecs.titleContains(selection.getTitlesPart()));
            }
            if (selection.getPriceMin() != 0) {
                spec = spec.and(ProductsSpecs.priceLesserThanOrEq(selection.getPriceMin()));
            }
            if (selection.getPriceMax() < Double.MAX_VALUE) {
                spec = spec.and(ProductsSpecs.priceGreaterThanOrEq(selection.getPriceMax()));
            }
        }
        return productRepository.findAll(spec, PageRequest.of(page, 10));
    }
}
