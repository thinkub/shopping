package com.ming.shopping.product.service;

import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@RequiredArgsConstructor
public abstract class AbstractProductCreateService {
    private final ProductRepository productRepository;

    @Transactional
    public ProductEntity createProduct(Product.Create create) {
        ProductEntity productEntity = ProductEntity.create(create);
        return productRepository.save(productEntity);
    }

    ProductEntity findProductByProductId(Long productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId);
        if (productEntity.isEmpty()) {
            throw new RuntimeException("선택하신 서브 상품정보가 존재 하지 않습니다.");
        }
        return productEntity.get();
    }
}
