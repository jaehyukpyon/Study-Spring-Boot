package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Category;
import com.springboot.api.data.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void relationshipTest() {
        // 테스트 데이터 생성
        Product product = new Product();
        product.setName("펜");
        product.setPrice(2000);
        product.setStock(100);

        productRepository.save(product);

        Category category = new Category();
        category.setCode("code1");
        category.setName("category books");
        category.getProducts().add(product);

        categoryRepository.save(category);

        // test code
        List<Product> products = categoryRepository.findById(1L).get().getProducts();

        for (Product findProduct : products) {
            System.out.println(product);
        }
    }

}