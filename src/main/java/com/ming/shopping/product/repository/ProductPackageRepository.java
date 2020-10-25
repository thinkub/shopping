package com.ming.shopping.product.repository;

import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.ProductPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public interface ProductPackageRepository extends JpaRepository<ProductPackageEntity, Long> {
    List<ProductPackageEntity> findByMainProduct(ProductEntity mainProduct);
}
