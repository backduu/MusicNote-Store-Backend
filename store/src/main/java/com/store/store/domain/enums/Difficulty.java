package com.store.store.domain.enums;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Difficulty {
    EASY("난이도 쉬움."),
    NORMAL("난이도 보통."),
    MEDIUM("난이도 중급."),
    HARD("난이도 어려움.");
    private final String description;
}
