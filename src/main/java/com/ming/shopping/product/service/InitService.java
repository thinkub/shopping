package com.ming.shopping.product.service;

import com.ming.shopping.product.component.ProductCreateServiceFactory;
import com.ming.shopping.product.model.DiscountType;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductCategory;
import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.repository.ProductRepository;
import com.ming.shopping.product.util.JsonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Service
@RequiredArgsConstructor
public class InitService {
    private final ProductCreateServiceFactory factory;
    private final ProductFinder productFinder;
    private final ProductRepository productRepository;

    private static final String JSON_PATH = "/static/products.json";

    @Transactional
    public void init() {
        List<Product.Create> creates = JsonUtils.getJsonData(Product.Create.class, JSON_PATH);

        List<Product> products = new ArrayList<>();
        for (Product.Create create : creates) {
            ProductCreatable service = factory.get(create.getSaleType());
            Long productId = service.create(create);
            Product product = productFinder.findProduct(productId);
            products.add(product);
        }
        initBundleProduct(products);
        initSaleOption(products);
    }

    private void initBundleProduct(List<Product> products) {
        List<Product> subProducts = products.stream()
                        .filter(p -> p.getCategory().isPants() || p.getCategory().isHat())
                        .collect(Collectors.toList());
        List<Product.CreateSub> subs = subProducts.stream()
                .map(s -> Product.CreateSub.ofInit(s.getProductId(), DiscountType.NONE, new BigDecimal(0.0))).
                        collect(Collectors.toList());
        Product.Create create =
                Product.Create.ofInit("바지 + 모자", ProductCategory.PANTS, ProductSaleType.BUNDLE, 35000, 40000, true, DiscountType.NONE, new BigDecimal(0.0), subs);
        ProductCreatable service = factory.get(ProductSaleType.BUNDLE);
        service.create(create);
    }

    private void initSaleOption(List<Product> products) {
        List<Product> subProducts = products.stream()
                .filter(p -> p.getCategory().isShoes())
                .collect(Collectors.toList());
        List<Product.CreateSub> subs = subProducts.stream()
                .map(s -> Product.CreateSub.ofInit(s.getProductId(), DiscountType.RATE, new BigDecimal(10.0)))
                .collect(Collectors.toList());

        Product.Create create =
                Product.Create.ofInit("가방2 - 옵션상품(할인적용)", ProductCategory.BAG, ProductSaleType.SALE_OPTION, 500000, 600000, true, DiscountType.NONE, new BigDecimal(0.0), subs);
        ProductCreatable service = factory.get(ProductSaleType.SALE_OPTION);
        service.create(create);
    }
}
