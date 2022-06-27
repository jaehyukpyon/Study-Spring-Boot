package com.springboot.api.data.repository.support;

import com.springboot.api.data.entity.Product;
import com.springboot.api.data.entity.QProduct;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findByName(String name) {
        QProduct qProduct = QProduct.product;

        List<Product> productList = this.from(qProduct)
                                        .where(qProduct.name.eq(name))
                                        .select(qProduct)
                                        .fetch();

        return productList;
    }



}
