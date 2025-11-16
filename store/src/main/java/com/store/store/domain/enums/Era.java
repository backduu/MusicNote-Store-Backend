package com.store.store.domain.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Era {
    BAROQUE("바로크 시대 (1600~1750, 바흐·헨델 등)"),
    CLASSICAL("고전주의 시대 (1750~1820, 모차르트·하이든 등)"),
    ROMANTIC("낭만주의 시대 (1820~1900, 쇼팽·리스트·브람스 등)"),
    MODERN("근·현대 시대 (20세기 이후, 드뷔시·스트라빈스키 등)");

    private final String description;
}
