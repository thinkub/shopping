package com.ming.shopping.product.service;

import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductSaleType;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public interface ProductCreatable {
    ProductSaleType getProductSaleType();

    Long create(Product.Create create);
}
