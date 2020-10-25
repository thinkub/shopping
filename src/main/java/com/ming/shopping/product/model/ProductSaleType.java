package com.ming.shopping.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Getter
@AllArgsConstructor
public enum ProductSaleType {
    SINGLE("단품상품", ProductSaleConditionType.NONE),
    ONE_PLUS_ONE("1 + 1 상품", ProductSaleConditionType.NONE),
    BUNDLE("묶음상품", ProductSaleConditionType.AND),
    SALE_OPTION("추가 구매시 할인 상품", ProductSaleConditionType.OR);

    private final String desc;
    private final ProductSaleConditionType saleConditionType;

    public boolean isSingleForCreate() {
        return this == SINGLE || this == ONE_PLUS_ONE;
    }
}
