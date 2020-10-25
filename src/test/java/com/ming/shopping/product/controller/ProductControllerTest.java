package com.ming.shopping.product.controller;

import com.ming.shopping.product.service.ProductFinder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author by Ming(thinkub0219@gmail.com) on 2020. 10. 25..
 */

@ExtendWith(SpringExtension.class)
@WebMvcTest(ProductController.class)
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ProductFinder productFinder;

    @Test
    void findProducts() {
    }

    @Test
    void findProduct() {
    }

    @Test
    void createProduct() {
    }
}