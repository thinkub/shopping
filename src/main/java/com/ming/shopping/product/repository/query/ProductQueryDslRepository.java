package com.ming.shopping.product.repository.query;

import com.ming.shopping.product.model.Product;

import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public interface ProductQueryDslRepository {
    List<Product> findProducts(Product.Search search);
}
