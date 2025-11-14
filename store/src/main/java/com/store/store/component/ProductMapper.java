package com.store.store.component;

import com.store.store.domain.entity.Product;
import com.store.store.domain.entity.ProductTag;
import com.store.store.domain.entity.Tag;
import com.store.store.domain.entity.User;
import com.store.store.domain.enums.MetricType;
import com.store.store.domain.enums.ProductStatus;
import com.store.store.dto.ProductDTO;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProductMapper {
    // entity -> dto
    public ProductDTO.Response toResponse(Product product) {
        // 상품의 ProductTags 내 LIKE 수, VIEW 수 얻기
        Set<String> tagNames = product.getProductTags().stream()
                .map(pt -> pt.getTag().getName())
                .collect(Collectors.toSet());

        // 메트릭 타입 별 값 추출
        Long viewValue = product.getProductTags().stream()
                .filter(pt -> pt.getMetricType() == MetricType.VIEW)
                .map(ProductTag::getValue)
                .findFirst()
                .orElse(0L);
        Long likeValue = product.getProductTags().stream()
                .filter(pt -> pt.getMetricType() == MetricType.LIKE)
                .map(ProductTag::getValue)
                .findFirst()
                .orElse(0L);


        return ProductDTO.Response.builder()
                .id(product.getId())
                .title(product.getTitle())
                .description(product.getDescription())
                .price(product.getPrice())
                .type(product.getType())
                .creator(product.getCreator())
                .previewUrl(product.getPreviewUrl())
                .genre(product.getGenre())
                .like(likeValue)
                .view(viewValue)
                .tags(tagNames)
                .filePath(product.getFilePath())
                .status(product.getStatus())
                .sellerId(product.getSeller().getId())
                .sellerName(product.getSeller().getName())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    // DTO → Entity
    public Product toEntity(ProductDTO.Request dto, User seller, Set<Tag> tags) {
        Product product = Product.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .type(dto.getType())
                .creator(dto.getCreator())
                .previewUrl(dto.getPreviewUrl())
                .filePath(dto.getFilePath())
                .status(ProductStatus.ONSALE)
                .seller(seller)
                .build();

        // product tag 생성
        Set<ProductTag> productTags = tags.stream()
                .map(tag -> new ProductTag(product, tag))
                .collect(Collectors.toSet());

        product.setProductTags(productTags);

        return product;
    }
}
