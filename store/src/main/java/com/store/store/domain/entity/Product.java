package com.store.store.domain.entity;

import com.store.store.domain.enums.ProductStatus;
import com.store.store.domain.enums.ProductType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Table(name="products")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Getter
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 상품명
    @Column(nullable = false, length = 100)
    private String title;

    // 상품 설명
    @Column(nullable = false, length = 2000)
    private String description;

    // 가격
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    // 상품 타입 (음반/악보)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductType type;

    // 미리보기 URL (예: 악보 이미지, 음원 샘플)
    private String previewUrl;

    // 실제 파일 경로
    private String filePath;

    // 상품 상태 (판매중, 비공개, 품절 등)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status;

    // 판매자 (User와 다대일 관계)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    // 태그 (장르, 악기 등)
    @ManyToMany
    @JoinTable(
            name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

}
