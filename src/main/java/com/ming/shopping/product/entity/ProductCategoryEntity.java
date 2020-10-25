package com.ming.shopping.product.entity;

import com.ming.shopping.product.model.ProductCategory;
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
@Table(name = "product_category")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class ProductCategoryEntity {
    @Id
    @Column(name = "product_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productCategoryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "category", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @CreatedDate
    @Column(name = "register_datetime")
    private LocalDateTime registerDatetime;

    private ProductCategoryEntity (ProductEntity product, ProductCategory category) {
        this.product = product;
        this.category = category;
    }

    public static ProductCategoryEntity create(ProductEntity product, ProductCategory category) {
        return new ProductCategoryEntity(product, category);
    }
}
