package com.ming.shopping.product.service;

import java.math.BigDecimal;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 25..
 */
public interface ProductDiscountPriceComputable {
    long compute(long salePrice, BigDecimal discount);
}
