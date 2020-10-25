package com.ming.shopping.product.model;

import com.ming.shopping.product.service.ProductDiscountPriceComputable;

import java.math.BigDecimal;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public enum DiscountType implements ProductDiscountPriceComputable {
    RATE {
        @Override
        public long compute(long salePrice, BigDecimal discount) {
            BigDecimal rate = new BigDecimal(100.0).subtract(discount);
            return rate.multiply(new BigDecimal(salePrice / 100)).longValue();
        }
    },
    VALUE {
        @Override
        public long compute(long salePrice, BigDecimal discount) {
            return  salePrice - discount.longValue();
        }
    },
    NONE {
        @Override
        public long compute(long salePrice, BigDecimal discount) {
            return salePrice;
        }
    }
}
