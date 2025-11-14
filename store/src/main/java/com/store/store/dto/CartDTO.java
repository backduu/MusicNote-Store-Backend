package com.store.store.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemRequest {
        private Long productId;   // 담을 상품 ID
        private int quantity;     // 담을 수량
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ItemResponse {
        private Long productId;
        private String title;
        private BigDecimal price;
        private int quantity;
        private BigDecimal totalPrice; // price * quantity
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CartResponse {
        private Long cartId;
        private Long userId;
        private String userName;
        private List<ItemResponse> items;
        private BigDecimal totalAmount; // 장바구니 전체 합계
    }
}
