package com.store.store.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MetricType {
    LIKE("좋아요"),
    VIEW("조회수"),
    RECOMMEND("추천"),
    REMARKED("표시"),
    SHARE("공유하기"),
    DOWNLOAD("다운로드하기");

    private final String description;
}
