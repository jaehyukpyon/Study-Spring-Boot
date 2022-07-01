package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Product;
import com.springboot.api.data.entity.ProductDetail;
import com.springboot.api.data.entity.Provider;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

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

    @PersistenceContext
    private EntityManager entityManager;

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

        providerRepository.findById(provider.getId()).get();

        //List<Product> products = providerRepository.findById(provider.getId()).get().getProductList();

//        for (Product product : products) {
//            System.out.println(product);
//        }

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

    //@Test
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


    // pg 285
    @Test
    public void cascadeTest() {
        Provider provider =this.savedProvider("새로운 공급업체");

        Product product1 = this.savedProduct("상품1", 1000, 1000);
        Product product2 = this.savedProduct("상품2", 500, 1500);
        Product product3 = this.savedProduct("상품3", 750, 500);

        // 연관 관계 설정
        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.save(provider);
    }

    private Provider savedProvider(String name) {
        Provider provider = new Provider();
        provider.setName(name);

        return provider;
    }

    private Product savedProduct(String name, Integer price, Integer stock) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        return product;
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void orphanRemovalTest() {
        Provider provider = this.savedProvider("새로운 공급업체");

        Product product1 = this.savedProduct("상품1", 1000, 1000);
        Product product2 = this.savedProduct("상품2", 500, 1500);
        Product product3 = this.savedProduct("상품3", 750, 500);

        product1.setProvider(provider);
        product2.setProvider(provider);
        product3.setProvider(provider);

        provider.getProductList().addAll(Lists.newArrayList(product1, product2, product3));

        providerRepository.saveAndFlush(provider);
        System.out.println("---------- provider savedAndFlush ends...\r\n");

        providerRepository.findAll().forEach(System.out::println);
        System.out.println("---------- provider findAll ends...\r\n");

        productRepository.findAll().forEach(System.out::println);
        System.out.println("---------- product findAll ends...\r\n");

        /*Provider foundProvider = providerRepository.findById(1L).get();
        foundProvider.getProductList().remove(0);*/


    }

    @Test
    public void myTest() {
        Product product = this.savedProduct("테스트 프로덕트", 1000, 10);

        productRepository.save(product);

        productRepository.findById(1L);

        /*select
        product0_.number as number1_1_0_,
                product0_.created_at as created_2_1_0_,
        product0_.updated_at as updated_3_1_0_,
                product0_.name as name4_1_0_,
        product0_.price as price5_1_0_,
                product0_.provider_id as provider7_1_0_,
        product0_.stock as stock6_1_0_,
                provider1_.id as id1_5_1_,
        provider1_.created_at as created_2_5_1_,
                provider1_.updated_at as updated_3_5_1_,
        provider1_.name as name4_5_1_,
                productdet2_.id as id1_2_2_,
        productdet2_.created_at as created_2_2_2_,
                productdet2_.updated_at as updated_3_2_2_,
        productdet2_.description as descript4_2_2_,
                productdet2_.product_number as product_5_2_2_
        from
            product product0_
                left outer join
                    provider provider1_
                    on product0_.provider_id=provider1_.id
                left outer join
                    product_detail productdet2_
                    on product0_.number=productdet2_.product_number
        where
        product0_.number=?*/
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void myTest2() {
        Product product1 = this.savedProduct("테스트 프로덕트1", 1000, 10);
        Product product2 = this.savedProduct("테스트 프로덕트2", 1000, 10);
        Product product3 = this.savedProduct("테스트 프로덕트3", 1000, 10);
        Product product4 = this.savedProduct("테스트 프로덕트4", 1000, 10);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.saveAndFlush(product4);

        //productRepository.findById(1L); // 아래 findAll() 주석하고 이 문장 실행 시, select query 실행 X

        productRepository.findAll(); // 위 findById(1L) 주석하고, 이 문장 실행 시 아래의 select query 단 한 번 실행
        /*
        * select
        product0_.number as number1_1_,
        product0_.created_at as created_2_1_,
        product0_.updated_at as updated_3_1_,
        product0_.name as name4_1_,
        product0_.price as price5_1_,
        product0_.provider_id as provider7_1_,
        product0_.stock as stock6_1_
    from
        product product0_*/
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void myTest3() {
        Product product1 = this.savedProduct("테스트 프로덕트1", 1000, 10);
        Product product2 = this.savedProduct("테스트 프로덕트2", 1000, 10);
        Product product3 = this.savedProduct("테스트 프로덕트3", 1000, 10);
        Product product4 = this.savedProduct("테스트 프로덕트4", 1000, 10);

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.saveAndFlush(product4);

        entityManager.clear();

        productRepository.findAll();

        /*
        * 일단 맨 처음
        * select
        product0_.number as number1_1_,
        product0_.created_at as created_2_1_,
        product0_.updated_at as updated_3_1_,
        product0_.name as name4_1_,
        product0_.price as price5_1_,
        product0_.provider_id as provider7_1_,
        product0_.stock as stock6_1_
    from
        product product0_
        *
        *
        * 위 쿼리를 단 한 번 수행하고, 아래 쿼리를 총 네 번 수행하는데, 그때 parameter에 binding되는 값은 1, 2, 3, 4
        *
        * select
        productdet0_.id as id1_2_2_,
        productdet0_.created_at as created_2_2_2_,
        productdet0_.updated_at as updated_3_2_2_,
        productdet0_.description as descript4_2_2_,
        productdet0_.product_number as product_5_2_2_,
        product1_.number as number1_1_0_,
        product1_.created_at as created_2_1_0_,
        product1_.updated_at as updated_3_1_0_,
        product1_.name as name4_1_0_,
        product1_.price as price5_1_0_,
        product1_.provider_id as provider7_1_0_,
        product1_.stock as stock6_1_0_,
        provider2_.id as id1_5_1_,
        provider2_.created_at as created_2_5_1_,
        provider2_.updated_at as updated_3_5_1_,
        provider2_.name as name4_5_1_
    from
        product_detail productdet0_
    left outer join
        product product1_
            on productdet0_.product_number=product1_.number // ProductDetail 중에서, product_number(Product)의 값이 Product와 일치하는 거 찾기.
    left outer join
        provider provider2_
            on product1_.provider_id=provider2_.id
    where
        productdet0_.product_number=?
        * */
    }

}