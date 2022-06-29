package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Product;
import com.springboot.api.data.entity.ProductDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductDetailRepositoryTest {

    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    //@Test
    public void saveAndReadTest1() {
        Product product = new Product();
        product.setName("스프링 부트 JPA");
        product.setPrice(5_000);
        product.setStock(500);

        productRepository.save(product);

        System.out.println("----------");

        ProductDetail productDetail = new ProductDetail();
        productDetail.setProduct(product);
        productDetail.setDescription("스프링 부트와 JPA를 공부할 수 있는 책");

        productDetailRepository.save(productDetail);

        System.out.println("----------");

        // 생성한 데이터 조회
        System.out.println("savedProduct 조회 >>"); 
        System.out.println("savedProduct : " + productDetailRepository.findById(productDetail.getId()).get().getProduct()); // 여기서 SELECT 쿼리 한 번 실행
        // savedProduct : Product(super=BaseEntity(createdAt=2022-06-29T14:24:32.581, updatedAt=2022-06-29T14:24:32.581), number=1, name=스프링 부트 JPA, price=5000, stock=500)
        // from
        //        product_detail productdet0_
        //    left outer join
        //        product product1_
        //            on productdet0_.product_number=product1_.number

        System.out.println("savedProductDetail 조회 >>");
        System.out.println("savedProductDetail : " + productDetailRepository.findById(productDetail.getId()).get()); // 여기서 SELECT 쿼리 한 번 더 실행
        // savedProductDetail : ProductDetail(super=BaseEntity(createdAt=2022-06-29T13:53:28.614, updatedAt=2022-06-29T13:53:28.614), id=1, description=null, product=Product(super=BaseEntity(createdAt=2022-06-29T13:53:28.501, updatedAt=2022-06-29T13:53:28.501), number=1, name=스프링 부트 JPA, price=5000, stock=500))
        // from
        //        product_detail productdet0_
        //    left outer join
        //        product product1_
        //            on productdet0_.product_number=product1_.number
        //    where
        //        productdet0_.id=?

        System.out.println("---------- end");
    }

}