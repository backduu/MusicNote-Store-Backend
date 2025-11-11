package com.store.store.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductStatus {
    ONSALE("판매중인 상품"),
    INACTIVE("비공개"),
    SOLDOUT("품절");

    private final String message;
}
