package com.ming.shopping.product.service;

import com.ming.shopping.product.entity.ProductCategoryEntity;
import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.ProductPackageEntity;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.repository.ProductCategoryRepository;
import com.ming.shopping.product.repository.ProductPackageRepository;
import com.ming.shopping.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Service
public class BundleProductCreateService extends AbstractProductCreateService implements ProductCreatable {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductPackageRepository productPackageRepository;

    public BundleProductCreateService(ProductRepository productRepository,
                                      ProductCategoryRepository productCategoryRepository,
                                      ProductPackageRepository productPackageRepository) {
        super(productRepository);
        this.productCategoryRepository = productCategoryRepository;
        this.productPackageRepository = productPackageRepository;
    }

    @Override
    public ProductSaleType getProductSaleType() {
        return ProductSaleType.BUNDLE;
    }

    @Override
    public Long create(Product.Create create) {
        ProductEntity createProduct = super.createProduct(create);

        Set<ProductCategoryEntity> categoryEntities = new HashSet<>();
        categoryEntities.add(ProductCategoryEntity.create(createProduct, createProduct.getCategory()));

        for (Long productId : create.getSubProductId()) {
            ProductEntity productEntity = super.findProductByProductId(productId);
            ProductPackageEntity packageEntity = ProductPackageEntity.create(createProduct, productEntity);
            productPackageRepository.save(packageEntity);

            categoryEntities.add(ProductCategoryEntity.create(productEntity, productEntity.getCategory()));
        }
        productCategoryRepository.saveAll(categoryEntities);
        return createProduct.getProductId();
    }
}
