package com.ming.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ming.shopping.product.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Getter
@NoArgsConstructor
public class Product {
    private Long productId;
    private String productName;
    private ProductCategory category;
    private String categoryDesc;
    private ProductSaleType saleType;
    private String saleTypeDesc;
    private long salePrice;
    private long originPrice;
    private DiscountType discountType;
    private BigDecimal discount;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registerDatetime;
    private List<Product> subProducts;

    private Product(ProductEntity entity) {
        this.productId = entity.getProductId();
        this.productName = entity.getProductName();
        this.category = entity.getCategory();
        this.categoryDesc = entity.getCategory().getDesc();
        this.saleType = entity.getSaleType();
        this.saleTypeDesc = entity.getSaleType().getDesc();
        this.salePrice = entity.getSalePrice();
        this.originPrice = entity.getOriginPrice();
        this.discountType = entity.getDiscountType();
        this.discount = entity.getDiscount();
        this.registerDatetime = entity.getRegisterDatetime();
    }

    private Product(ProductEntity entity, List<Product> subProducts) {
        this.productId = entity.getProductId();
        this.productName = entity.getProductName();
        this.category = entity.getCategory();
        this.categoryDesc = entity.getCategory().getDesc();
        this.saleType = entity.getSaleType();
        this.saleTypeDesc = entity.getSaleType().getDesc();
        this.salePrice = entity.getSalePrice();
        this.originPrice = entity.getOriginPrice();
        this.discountType = entity.getDiscountType();
        this.discount = entity.getDiscount();
        this.registerDatetime = entity.getRegisterDatetime();
        this.subProducts = subProducts;
    }

    public static Product ofDetail(ProductEntity main, List<ProductEntity> subs) {
        List<Product> subProducts = subs.stream().map(Product::ofEntity).collect(Collectors.toList());
        return new Product(main, subProducts);
    }

    private static Product ofEntity(ProductEntity entity) {
        return new Product(entity);
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    public static class Search {
        private String productName;
        private ProductCategory category;
        private ProductSaleType saleType;

        public static Search of(String productName, ProductCategory category, ProductSaleType saleType) {
            return Search.builder()
                    .productName(productName)
                    .category(category)
                    .saleType(saleType)
                    .build();
        }

        public boolean hasProductName() {
            return !StringUtils.isEmpty(this.productName);
        }

        public boolean hasCategory() {
            return this.category != null;
        }

        public boolean hasSaleType() {
            return this.saleType != null;
        }
    }

    @Getter
    public static class Create {
        private String productName;
        private ProductCategory category;
        private ProductSaleType saleType;
        private long salePrice;
        private long originPrice;
        private boolean display;
        private DiscountType discountType;
        private BigDecimal discount;
        private List<CreateSub> subProducts;
    }

    @Getter
    public static class CreateSub {
        private Long productId;
        private DiscountType discountType;
        private BigDecimal discount;
    }
}
