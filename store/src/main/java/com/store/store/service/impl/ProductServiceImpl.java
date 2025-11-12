package com.store.store.service.impl;

import com.store.store.component.ProductMapper;
import com.store.store.domain.entity.Product;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.domain.enums.ProductType;
import com.store.store.dto.ProductDTO;
import com.store.store.repository.ProductRepository;
import com.store.store.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Service("productServiceImpl")
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    // 금주의 새 상품 조회 (캐러셀 전용)
    @Override
    public List<ProductDTO.Response> findNewProductsForCarousel(int limit) {
        // 이번 주 첫 날
        LocalDateTime startOfWeek = LocalDateTime.now()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .withHour(0).withMinute(0).withSecond(0).withNano(0);

        // 이번 주 막 날
        LocalDateTime endOfWeek = startOfWeek.plusDays(7);

        List<Product> products = productRepository.findNewProducts(startOfWeek, endOfWeek, ProductStatus.ONSALE);

        return products.stream()
                .limit(limit)
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());

    }

    // 오늘의 상품 조회
    @Override
    public List<ProductDTO.Response> findTodayNewProducts() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);

        List<Product> products = productRepository.findNewProducts(startOfDay, endOfDay, ProductStatus.ONSALE);

        return products.stream()
                .map(ProductMapper::toResponse)
                .collect(Collectors.toList());
    }
}
