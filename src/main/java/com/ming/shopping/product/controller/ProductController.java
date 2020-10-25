package com.ming.shopping.product.controller;

import com.ming.shopping.product.component.ProductCreateServiceFactory;
import com.ming.shopping.product.model.Product;
import com.ming.shopping.product.model.ProductCategory;
import com.ming.shopping.product.model.ProductSaleType;
import com.ming.shopping.product.service.ProductCreatable;
import com.ming.shopping.product.service.ProductFinder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 24..
 */

@Api(tags = {"Product"})
@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductCreateServiceFactory createServiceFactory;
    private final ProductFinder productFinder;

    @ApiOperation(value = "상품 리스트")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "productName", value = "상품명", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "category", value = "상품 카테고리", dataType = "String", paramType = "query", allowableValues = "SHOES, BAG, T_SHIRT, PANTS, HAT, RING"),
            @ApiImplicitParam(name = "saleType", value = "상품 판매 타입", dataType = "String", paramType = "query", allowableValues = "SINGLE, ONE_PLUS_ONE, BUNDLE, SALE_OPTION")
    })
    @GetMapping("/products")
    public ResponseEntity<List<Product>> findProducts(@RequestParam(required = false) String productName,
                                                      @RequestParam(required = false) ProductCategory category,
                                                      @RequestParam(required = false) ProductSaleType saleType) {
        Product.Search search = Product.Search.of(productName, category, saleType);
        List<Product> products = productFinder.findProducts(search);
        return ResponseEntity.ok().body(products);
    }

    @ApiOperation(value = "상품 상세")
    @GetMapping("/product/{productId}")
    @ApiImplicitParam(name = "productId", value = "상품 아이디", dataType = "long", required = true, paramType = "path")
    public ResponseEntity<Product> findProduct(@PathVariable Long productId) {
        Product product = productFinder.findProduct(productId);
        return ResponseEntity.ok().body(product);
    }

    @PostMapping("/product")
    public ResponseEntity<Long> createProduct(@RequestBody Product.Create create) {
        ProductCreatable service = createServiceFactory.get(create.getSaleType());
        Long productId = service.create(create);
        return ResponseEntity.ok().body(productId);
    }
}
