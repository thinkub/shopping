package com.ming.shopping.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Getter
@AllArgsConstructor
public enum ProductSaleType {
    NONE("단품상품"),
    ONE_PLUS_ONE("1 + 1 상품"),
    BUNDLE("묶음상품"),
    SALE_OPTION("추가 구매시 할인 상품");

    private final String desc;
}
