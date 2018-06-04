package com.daklan.thymeleafcourse.services;

import com.daklan.thymeleafcourse.domain.Product;

import java.util.List;

public interface ProductService {

    Product getProduct(Integer id);
    List<Product> listProducts();

}
