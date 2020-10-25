package com.ming.shopping.product.service;

import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.ProductPackageEntity;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.repository.ProductPackageRepository;
import com.ming.shopping.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Service
@RequiredArgsConstructor
public class ProductFinder {
    private final ProductRepository productRepository;
    private final ProductPackageRepository productPackageRepository;

    public List<Product> findProducts(Product.Search search) {
        return productRepository.findProducts(search);
    }

    public Product findProduct(Long productId) {
        Optional<ProductEntity> productOptional = productRepository.findById(productId);
        if (productOptional.isEmpty()) {
            throw new RuntimeException("상품을 찾을 수 없습니다.");
        }
        ProductEntity productEntity = productOptional.get();
        List<ProductPackageEntity> productPackageEntities = productPackageRepository.findByMainProduct(productEntity);

        return Product.ofDetail(productEntity, productPackageEntities);
    }
}
