package com.kaishengit.service;

import com.kaishengit.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int number);
}
