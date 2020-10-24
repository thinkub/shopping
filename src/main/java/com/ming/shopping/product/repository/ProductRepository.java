package com.ming.shopping.product.repository;

import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.repository.query.ProductQueryDslRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public interface ProductRepository extends JpaRepository<ProductEntity, Long>, ProductQueryDslRepository {
}
