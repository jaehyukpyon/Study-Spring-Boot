package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Product;
import com.springboot.api.data.entity.ProductDetail;
import com.springboot.api.data.entity.Provider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityNotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProviderRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProductDetailRepository productDetailRepository;

    //@Test
    public void relationshipTest1() {
        // 테스트 데이터 생성
        Provider provider = new Provider();
        provider.setName("ㅇㅇ물산");

        providerRepository.save(provider);

        System.out.println("----------\r\n");

        Product product = new Product();
        product.setName("가위");
        product.setPrice(5_000);
        product.setStock(500);
        product.setProvider(provider);

        productRepository.save(product);

        System.out.println("----------\r\n");

        System.out.println(
                "product : " + productRepository.findById(1L).orElseThrow(EntityNotFoundException::new)
        ); // 여기서 select 쿼리 한 번 실행
        // product : Product(super=BaseEntity(createdAt=2022-06-29T17:45:01.519, updatedAt=2022-06-29T17:45:01.519), number=1, name=가위, price=5000, stock=500)

        System.out.println("----------\r\n");

        System.out.println("provider : " + productRepository.findById(1L).orElseThrow(EntityNotFoundException::new).getProvider());
        // 여기서 select 쿼리 한 번 실행
        // provider : Provider(super=BaseEntity(createdAt=2022-06-29T17:45:01.388, updatedAt=2022-06-29T17:45:01.388), id=1, name=ㅇㅇ물산)

        /* page 265 references
        * select
        product0_.number as number1_0_0_,
        product0_.created_at as created_2_0_0_,
        product0_.updated_at as updated_3_0_0_,
        product0_.name as name4_0_0_,
        product0_.price as price5_0_0_,
        product0_.provider_id as provider7_0_0_,
        product0_.stock as stock6_0_0_,
        provider1_.id as id1_2_1_,
        provider1_.created_at as created_2_2_1_,
        provider1_.updated_at as updated_3_2_1_,
        provider1_.name as name4_2_1_,
        productdet2_.id as id1_1_2_,
        productdet2_.created_at as created_2_1_2_,
        productdet2_.updated_at as updated_3_1_2_,
        productdet2_.description as descript4_1_2_,
        productdet2_.product_number as product_5_1_2_
    from
        product product0_
    left outer join
        provider provider1_
            on product0_.provider_id=provider1_.id
    left outer join
        product_detail productdet2_
            on product0_.number=productdet2_.product_number
    where
        product0_.number=1;
        * */
    }

    @Test
    public void relationshipTest2() {
        // test data generate
        Provider provider = new Provider();
        provider.setName("ㅇㅇ 상사");

        providerRepository.save(provider);

        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(2_000);
        product1.setStock(100);
        product1.setProvider(provider);

        Product product2 = new Product();
        product2.setName("가방");
        product2.setPrice(20_000);
        product2.setStock(200);
        product2.setProvider(provider);

        Product product3 = new Product();
        product3.setName("노트");
        product3.setPrice(3_000);
        product3.setStock(1_000);
        product3.setProvider(provider);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

        for (Product product : products) {
            System.out.println(product);
        }

        /*
        * select
        provider0_.id as id1_2_0_,
        provider0_.created_at as created_2_2_0_,
        provider0_.updated_at as updated_3_2_0_,
        provider0_.name as name4_2_0_,
        productlis1_.provider_id as provider7_0_1_,
        productlis1_.number as number1_0_1_,
        productlis1_.number as number1_0_2_,
        productlis1_.created_at as created_2_0_2_,
        productlis1_.updated_at as updated_3_0_2_,
        productlis1_.name as name4_0_2_,
        productlis1_.price as price5_0_2_,
        productlis1_.provider_id as provider7_0_2_,
        productlis1_.stock as stock6_0_2_,
        productdet2_.id as id1_1_3_,
        productdet2_.created_at as created_2_1_3_,
        productdet2_.updated_at as updated_3_1_3_,
        productdet2_.description as descript4_1_3_,
        productdet2_.product_number as product_5_1_3_
    from
        provider provider0_
    left outer join
        product productlis1_
            on provider0_.id=productlis1_.provider_id
    left outer join
        product_detail productdet2_
            on productlis1_.number=productdet2_.product_number
    where
        provider0_.id=1;
        * */
    }

    @Test
    public void myTest1() {
        Provider provider = new Provider();
        provider.setName("프로바이더1");

        providerRepository.save(provider);

        Product product = new Product();
        product.setName("test item1");
        product.setPrice(9999);
        product.setStock(100);
        product.setProvider(provider);

        productRepository.save(product);

        ProductDetail productDetail = new ProductDetail();
        productDetail.setDescription("just item 1");
        productDetail.setProduct(product);

        productDetailRepository.save(productDetail);

        System.out.println("쿼리 시작----------\r\n");

        System.out.println(productRepository.findById(product.getNumber()).get());

        System.out.println("쿼리 끝----------\r\n");
    }

}