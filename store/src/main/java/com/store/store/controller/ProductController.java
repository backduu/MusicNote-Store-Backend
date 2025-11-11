package com.store.store.controller;

import com.store.store.dto.ProductDTO;
import com.store.store.service.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Product API", description = "상품(악보/음원/음반) 관련 API (금주의 추천 아이템, 신규 아이템 등")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/carousel/new")
    public ResponseEntity<List<ProductDTO.Response>> getWeeklyNewProducts(
        @RequestParam(defaultValue = "5") int limit
    ) {
        return ResponseEntity.ok(productService.findNewProductsForCarousel(limit));
    }
}
