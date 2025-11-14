package com.store.store.repository;

import com.store.store.domain.entity.Product;
import com.store.store.domain.enums.MetricType;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.domain.enums.ProductType;
import com.store.store.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p FROM Product p " +
            "WHERE p.createdAt BETWEEN :start AND :end " +
            "AND p.status = :status " +
            "ORDER BY p.createdAt DESC ")
    List<Product> findNewProducts(LocalDateTime start, LocalDateTime end, ProductStatus status);

    @Query("""
            SELECT p
            FROM Product p
            JOIN ProductTag pt ON pt.product = p
            WHERE p.status = :status
              AND pt.metricType = :metricType
              AND p.createdAt BETWEEN :start AND :end
            ORDER BY pt.value DESC
    """)
    List<Product> findTopProductsByMetricAndPeriod(
            @Param("status") ProductStatus status,
            @Param("metricType") MetricType metricType,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            Pageable pageable
    );
}
