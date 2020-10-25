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

import javax.transaction.Transactional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Service
public class SaleOptionProductCreateService extends AbstractProductCreateService implements ProductCreatable {
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductPackageRepository productPackageRepository;

    public SaleOptionProductCreateService(ProductRepository productRepository,
                                      ProductCategoryRepository productCategoryRepository,
                                      ProductPackageRepository productPackageRepository) {
        super(productRepository);
        this.productCategoryRepository = productCategoryRepository;
        this.productPackageRepository = productPackageRepository;
    }

    @Override
    public ProductSaleType getProductSaleType() {
        return ProductSaleType.SALE_OPTION;
    }

    @Override
    @Transactional
    public Long create(Product.Create create) {
        ProductEntity createProduct = super.createProduct(create);

        ProductCategoryEntity categoryEntity = ProductCategoryEntity.create(createProduct, createProduct.getCategory());
        productCategoryRepository.save(categoryEntity);

        for (Product.CreateSub subProduct : create.getSubProducts()) {
            ProductEntity productEntity = super.findProductAndValidSubProductByProductId(subProduct.getProductId());
            ProductPackageEntity packageEntity = ProductPackageEntity.createSaleOptionProduct(createProduct, productEntity, subProduct);
            productPackageRepository.save(packageEntity);
        }
        return createProduct.getProductId();
    }
}
