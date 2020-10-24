package com.ming.shopping.product.component;

import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.service.ProductCreatable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Component
public class ProductCreateServiceFactory {
    private final Map<ProductSaleType, ProductCreatable> serviceMap;

    @Autowired
    public ProductCreateServiceFactory(List<ProductCreatable> services) {
        this.serviceMap = new EnumMap<>(ProductSaleType.class);
        for (ProductCreatable service : services) {
            this.serviceMap.put(service.getProductSaleType(), service);
        }
    }

    /**
     * 상품 생성 서비스 구현체는 단품상품과 1+1상품이 동일 로직으로 구현됨
     *
     * @param type
     * @return
     */
    public ProductCreatable get(ProductSaleType type) {
        ProductSaleType saleType =  type.isSingleForCreate() ? ProductSaleType.SINGLE : type;
        ProductCreatable service = this.serviceMap.get(saleType);
        return Optional.ofNullable(service).orElseThrow(IllegalArgumentException::new);
    }
}
