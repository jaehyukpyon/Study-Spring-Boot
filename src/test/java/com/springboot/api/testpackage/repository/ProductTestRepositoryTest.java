package com.springboot.api.testpackage.repository;

import com.springboot.api.testpackage.entity.ProductDetailTest;
import com.springboot.api.testpackage.entity.ProductTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductTestRepositoryTest {

    @Autowired
    private ProductTestRepository productTestRepository;

    @Autowired
    private ProductDetailTestRepository productDetailTestRepository;

    @Test
    public void test1() {
        ProductTest productTest = new ProductTest();
        productTest.setProductName("상품명 1");
        productTestRepository.save(productTest);

        ProductDetailTest productDetailTest = new ProductDetailTest();
        productDetailTest.setProductDetailDescription("상품 상세 설명입니다.");
        productDetailTest.setProductTest(productTest);

        productDetailTestRepository.save(productDetailTest);

        System.out.println("savedProductTest : " + productDetailTestRepository.findById(productDetailTest.getProductDetailId()).get().getProductTest());

        System.out.println("----------\r\n");

        System.out.println("savedProductDetailTest : " + productDetailTestRepository.findById(productDetailTest.getProductDetailId()).get());
    }

    @Test
    public void tes2() {
       ProductTest productTest = new ProductTest();
       productTest.setProductName("상품명입니다.");

       productTestRepository.save(productTest);

       productTestRepository.findById(productTest.getProductId());

       /*
       *
        select
        producttes0_.product_id as product_1_3_0_,
        producttes0_.product_name as product_2_3_0_,
        productdet1_.product_detail_id as product_1_2_1_,
        productdet1_.product_detail_description as product_2_2_1_,
        productdet1_.product_test_fk as product_3_2_1_
    from
        product_test producttes0_
    left outer join
        product_detail_test productdet1_
            on producttes0_.product_id=productdet1_.product_test_fk
    where
        producttes0_.product_id=1;
       * */
    }

}