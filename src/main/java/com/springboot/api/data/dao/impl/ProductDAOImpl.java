package com.springboot.api.data.dao.impl;

import com.springboot.api.data.dao.ProductDAO;
import com.springboot.api.data.entity.Product;
import com.springboot.api.data.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ProductDAOImpl implements ProductDAO {

    private final ProductRepository productRepository;

    @Autowired
    public ProductDAOImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product insertProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product selectProduct(Long number) {
        System.out.println("ProductDAOImpl selectProduct(Long number) starts...");
        Product selectedProduct = productRepository.getById(number);

        System.out.println(selectedProduct.getClass().getName()); // com.springboot.api.data.entity.Product$HibernateProxy$RoNXS8yV

        System.out.println("ProductDAOImpl selectProduct(Long number) ends..."); // 이 메서드 안에서는 실제 select 쿼리가 실행 되지 않음
        return selectedProduct;
    }

    @Override
    public Product updateProductName(Long number, String name) throws Exception {
        Product testProduct = new Product();
        System.out.println("testProduct : " + testProduct.getClass().getName()); // testProduct : com.springboot.api.data.entity.Product

        Optional<Product> selectedProduct = productRepository.findById(number); // 여기에서 select 쿼리 한 번 실행.

        System.out.println("--------------------");

        Product updatedProduct = null;

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();
            System.out.println("product : " + product.getClass().getName()); // product : com.springboot.api.data.entity.Product

            product.setName(name);
            product.setUpdatedAt(LocalDateTime.now());

            updatedProduct = productRepository.save(product); // select 쿼리가 실행되지 않고, 바로 update 쿼리 실행.
        } else {
            throw new Exception();
        }

        return updatedProduct;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        Optional<Product> selectedProduct = productRepository.findById(number);

        if (selectedProduct.isPresent()) {
            Product product = selectedProduct.get();

            productRepository.delete(product);
        } else {
            throw new Exception();
        }
    }

}
