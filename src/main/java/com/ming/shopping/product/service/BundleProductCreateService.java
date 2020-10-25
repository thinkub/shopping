package com.ming.shopping.product.service;

import com.ming.shopping.product.entity.ProductCategoryEntity;
import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.ProductPackageEntity;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductCategory;
import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.repository.ProductCategoryRepository;
import com.ming.shopping.product.repository.ProductPackageRepository;
import com.ming.shopping.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

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
    @Transactional
    public Long create(Product.Create create) {
        if (create.getSubProducts().size() < 2) {
            throw new RuntimeException("묶음 상품은 최소 2개의 서브상품을 등록해야 합니다.");
        }
        ProductEntity createProduct = super.createProduct(create);

        Map<ProductCategory, ProductCategoryEntity> categoryMap = new HashMap<>();
        categoryMap.put(createProduct.getCategory(), ProductCategoryEntity.create(createProduct, createProduct.getCategory()));

        for (Product.CreateSub subProduct : create.getSubProducts()) {
            ProductEntity productEntity = super.findProductAndValidSubProductByProductId(subProduct.getProductId());
            ProductPackageEntity packageEntity = ProductPackageEntity.createBundleProduct(createProduct, productEntity);
            productPackageRepository.save(packageEntity);
            categoryMap.put(productEntity.getCategory(), ProductCategoryEntity.create(createProduct, productEntity.getCategory()));
        }
        productCategoryRepository.saveAll(categoryMap.values());
        return createProduct.getProductId();
    }
}
