package com.ming.shopping.product.entity;

import com.ming.shopping.product.common.BooleanYnConverter;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductCategory;
import com.ming.shopping.product.model.ProductSaleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Entity
@Getter
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    @Column(name = "product_name", length = 100, nullable = false)
    private String productName;

    @Column(name = "main_category", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "sale_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSaleType saleType;

    @Column(name = "price")
    private long price;

    @Column(name = "display_yn")
    @Convert(converter = BooleanYnConverter.class)
    private boolean display;

    @CreatedDate
    @Column(name = "register_datetime")
    private LocalDateTime registerDatetime;

    private ProductEntity(Product.Create create) {
        this.productName = create.getProductName();
        this.category = create.getCategory();
        this.saleType = create.getSaleType();
        this.price = create.getPrice();
        this.display = create.isDisplay();
    }

    public static ProductEntity create(Product.Create create) {
        return new ProductEntity(create);
    }
}
