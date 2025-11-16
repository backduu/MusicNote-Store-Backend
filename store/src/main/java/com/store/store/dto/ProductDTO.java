package com.store.store.dto;

import com.store.store.domain.enums.Difficulty;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.domain.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class ProductDTO {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        private String title;
        private String description;
        private BigDecimal price;
        private ProductType type;
        private String creator;
        private String previewUrl;
        private String filePath;
        private Set<Long> tagIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private BigDecimal price;
        private ProductType type;
        private String genre;
        private String creator;
        private String previewUrl;
        private String filePath;
        private ProductStatus status;
        private Long sellerId;
        private String sellerName;
        private Long view;
        private Long like;
        private Difficulty difficulty;
        private String instrument;
        private Set<String> tags;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }

}
