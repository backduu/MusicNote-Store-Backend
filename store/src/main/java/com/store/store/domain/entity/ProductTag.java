package com.store.store.domain.entity;

import com.store.store.domain.enums.MetricType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "product_tags")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@Builder
public class ProductTag {
    public ProductTag(Product product, Tag tag) {
        this.product = product;
        this.tag = tag;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    private Tag tag;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MetricType metricType;

    @Column(nullable = false)
    private Long value;

    public void setProduct(Product product) {
        this.product = product;
    }
}
