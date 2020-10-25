package com.ming.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.ProductPackageEntity;
import lombok.*;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Product> subProducts;

    private Product(ProductPackageEntity productPackageEntity) {
        ProductEntity entity = productPackageEntity.getSubProduct();
        this.productId = entity.getProductId();
        this.productName = entity.getProductName();
        this.category = entity.getCategory();
        this.categoryDesc = entity.getCategory().getDesc();
        this.saleType = entity.getSaleType();
        this.saleTypeDesc = entity.getSaleType().getDesc();
        this.salePrice = productPackageEntity.getDiscountType().compute(entity.getSalePrice(), productPackageEntity.getDiscount());
        this.originPrice = entity.getOriginPrice();
        this.discountType = productPackageEntity.getDiscountType();
        this.discount = productPackageEntity.getDiscount();
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

    public static Product ofDetail(ProductEntity main, List<ProductPackageEntity> subs) {
        List<Product> subProducts = subs.stream().map(Product::ofSubProduct).collect(Collectors.toList());
        return new Product(main, subProducts);
    }

    private static Product ofSubProduct(ProductPackageEntity entity) {
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
    @Builder(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
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

        public static Create ofInit(String productName, ProductCategory category, ProductSaleType saleType,
                                    long salePrice, long originPrice, boolean display, DiscountType discountType,
                                    BigDecimal discount, List<CreateSub> subProducts) {
            return Create.builder()
                    .productName(productName)
                    .category(category)
                    .saleType(saleType)
                    .salePrice(salePrice)
                    .originPrice(originPrice)
                    .display(display)
                    .discountType(discountType)
                    .discount(discount)
                    .subProducts(subProducts)
                    .build();
        }
    }

    @Getter
    @Builder(access = AccessLevel.PRIVATE)
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    public static class CreateSub {
        private Long productId;
        private DiscountType discountType;
        private BigDecimal discount;

        public static CreateSub ofInit(Long productId, DiscountType discountType, BigDecimal discount) {
            return CreateSub.builder()
                    .productId(productId)
                    .discountType(discountType)
                    .discount(discount)
                    .build();
        }
    }
}
