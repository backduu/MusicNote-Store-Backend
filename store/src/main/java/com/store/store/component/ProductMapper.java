package com.store.store.component;

import com.store.store.domain.entity.Product;
import com.store.store.domain.entity.Tag;
import com.store.store.domain.entity.User;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    // entity -> dto
    public static ProductDTO.Response toResponse(Product product) {
        return ProductDTO.Response.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .type(product.getType())
                .creator(product.getCreator())
                .previewUrl(product.getPreviewUrl())
                .filePath(product.getFilePath())
                .status(product.getStatus())
                .sellerId(product.getSeller().getId())
                .sellerName(product.getSeller().getName())
                .tags(product.getTags().stream()
                        .map(tag -> tag.getName())
                        .collect(Collectors.toSet()))
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    // DTO → Entity
    public static Product toEntity(ProductDTO.Request dto, User seller, Set<Tag> tags) {
        return Product.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .type(dto.getType())
                .creator(dto.getCreator())
                .previewUrl(dto.getPreviewUrl())
                .filePath(dto.getFilePath())
                .status(ProductStatus.ONSALE)
                .seller(seller)
                .tags(tags)
                .build();
    }
}
