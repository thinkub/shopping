package com.ming.shopping.product.entity;

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

    @Column(name = "category", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "sale_type", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductSaleType saleType;

    @Column(name = "price")
    private long price;

    @CreatedDate
    @Column(name = "register_datetime")
    private LocalDateTime registerDatetime;
}
