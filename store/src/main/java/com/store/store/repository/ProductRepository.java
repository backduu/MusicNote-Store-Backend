package com.store.store.repository;

import com.store.store.domain.entity.Product;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.dto.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p " +
            "WHERE p.createdAt BETWEEN :start AND :end " +
            "AND p.status = :status " +
            "ORDER BY p.createdAt DESC")
    List<Product> findNewProductsForCarousel(LocalDateTime start, LocalDateTime end, ProductStatus status);

}
