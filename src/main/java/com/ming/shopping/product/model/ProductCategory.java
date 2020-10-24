package com.ming.shopping.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Getter
@AllArgsConstructor
public enum ProductCategory {
    SHOES("신발"),
    BAG("가방"),
    T_SHIRT("티셔츠"),
    PANTS("바지"),
    HAT("모자"),
    RING("반지");

    private final String desc;

    public boolean isPants() {
        return this == PANTS;
    }

    public boolean isHat() {
        return this == HAT;
    }

    public boolean isBag() {
        return this == BAG;
    }

    public boolean isShoes() {
        return this == SHOES;
    }
}
