package com.springboot.api.data.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.api.data.entity.Product;
import com.springboot.api.data.entity.QProduct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@SpringBootTest
public class QueryDSLTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    //@Test
    public void queryDSLTest() {
        JPAQuery<Product> query = new JPAQuery<>(entityManager);

        QProduct qProduct = QProduct.product;

        List<Product> productList = query
                                        .from(qProduct)
                                        .where(qProduct.name.eq("펜"))
                                        .orderBy(qProduct.price.asc())
                                        .fetch();
    }

    //@Test
    public void queryDSLTest4() {
        QProduct qProduct = QProduct.product;

        List<String> productList = jpaQueryFactory
                                    .select(qProduct.name)
                                    .from(qProduct)
                                    .where(qProduct.name.eq("펜"))
                                    .orderBy(qProduct.price.asc())
                                    .fetch();
    }

}
