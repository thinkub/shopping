package com.ming.shopping.product.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Getter
public class Product {
    private Long productId;
    private String productName;
    private ProductCategory category;
    private String categoryDesc;
    private ProductSaleType saleType;
    private String saleTypeDesc;
    private long price;
    @JsonFormat(pattern = "yyyy.MM.dd HH:mm:ss")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registerDatetime;

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
        private long price;
        private boolean display;
        private List<Long> subProductId;
    }
}
