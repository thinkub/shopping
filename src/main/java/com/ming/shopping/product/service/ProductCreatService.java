package com.ming.shopping.product.service;

import com.ming.shopping.product.entity.ProductCategoryEntity;
import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.repository.ProductCategoryRepository;
import com.ming.shopping.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Service
public class ProductCreatService extends AbstractProductCreateService implements ProductCreatable {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCreatService(ProductRepository productRepository,
                               ProductCategoryRepository productCategoryRepository) {
        super(productRepository);
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public ProductSaleType getProductSaleType() {
        return ProductSaleType.SINGLE;
    }

    @Override
    @Transactional
    public Long create(Product.Create create) {
        ProductEntity productEntity = super.createProduct(create);

        ProductCategoryEntity categoryEntity = ProductCategoryEntity.create(productEntity, productEntity.getCategory());
        productCategoryRepository.save(categoryEntity);

        return productEntity.getProductId();
    }
}
