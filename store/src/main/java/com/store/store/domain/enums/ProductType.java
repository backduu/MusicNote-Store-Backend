package com.store.store.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductType {
    ALBUM("음반"),
    SONG("음원"),
    SHEET("악보");
    private final String description;
}
