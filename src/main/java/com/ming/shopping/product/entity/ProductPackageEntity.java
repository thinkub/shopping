package com.ming.shopping.product.entity;

import com.ming.shopping.product.model.DiscountType;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Entity
@Getter
@Table(name = "product_package")
@EntityListeners(AuditingEntityListener.class)
public class ProductPackageEntity {
    @Id
    @Column(name = "product_package_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productPackageId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "main_product_id", nullable = false)
    private ProductEntity mainProduct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sub_product_id", nullable = false)
    private ProductEntity subProduct;

    @Column(name = "sub_product_discount_type")
    @Enumerated(EnumType.STRING)
    private DiscountType discountType;

    @Column(name = "discount", precision = 10, scale = 3)
    private BigDecimal discount;

    @CreatedDate
    @Column(name = "register_datetime")
    private LocalDateTime registerDatetime;

    private ProductPackageEntity (ProductEntity mainProduct, ProductEntity subProduct) {
        this.mainProduct = mainProduct;
        this.subProduct = subProduct;
    }

    public static ProductPackageEntity create(ProductEntity mainProduct, ProductEntity subProduct) {
        return new ProductPackageEntity(mainProduct, subProduct);
    }
}
