package com.ming.shopping.product.repository.query;

import com.ming.shopping.product.entity.ProductEntity;
import com.ming.shopping.product.entity.QProductEntity;
import com.ming.shopping.product.model.Product;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */
public class ProductRepositoryImpl extends QuerydslRepositorySupport implements ProductQueryDslRepository {
    private final JPAQueryFactory queryFactory;
    private final QProductEntity product = QProductEntity.productEntity;

    public ProductRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(ProductEntity.class);
        this.queryFactory = jpaQueryFactory;
    }

    @Override
    public List<Product> findProducts(Product.Search search) {
        BooleanBuilder criteria = new BooleanBuilder();
        criteria.and(product.display.eq(true));
        if (search.hasProductName()) {
            criteria.and(product.productName.contains(search.getProductName()));
        }
        if (search.hasCategory()) {
            criteria.and(product.category.eq(search.getCategory()));
        }
        if (search.hasSaleType()) {
            criteria.and(product.saleType.eq(search.getSaleType()));
        }

        return queryFactory.from(product)
                .select(Projections.fields(Product.class,
                        product.productId,
                        product.productName,
                        product.category,
                        product.saleType,
                        product.salePrice,
                        product.originPrice,
                        product.discountType,
                        product.discount,
                        product.registerDatetime))
                .where(criteria)
                .fetch();
    }
}
