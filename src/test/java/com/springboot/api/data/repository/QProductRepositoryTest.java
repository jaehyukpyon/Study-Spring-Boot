package com.springboot.api.data.repository;

import com.querydsl.core.types.Predicate;
import com.springboot.api.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QProductRepositoryTest {

    @Autowired
    private QProductRepository qProductRepository;

    @Test
    public void queryDSLTest1() {
        Predicate predicate = QProduct.product.name.containsIgnoreCase("펜")
                                        .and(QProduct.product.price.between(1000, 2500));


    }

}