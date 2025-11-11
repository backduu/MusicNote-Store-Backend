package com.store.store.service;

import com.store.store.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO.Response> findNewProductsForCarousel(int limit);
}

