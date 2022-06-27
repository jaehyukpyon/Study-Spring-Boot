package com.springboot.api.service.impl;

import com.springboot.api.data.dao.ProductDAO;
import com.springboot.api.data.dto.ProductDto;
import com.springboot.api.data.dto.ProductResponseDto;
import com.springboot.api.data.entity.Product;
import com.springboot.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Qualifier(value = "productServiceImpl1")
@Service
public class ProductServiceImpl1 implements ProductService {

    private final ProductDAO productDAO;

    @Autowired
    public ProductServiceImpl1(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        System.out.println("ProductServiceImpl getProduct starts...");
        Product product = productDAO.selectProduct(number); // proxy 객체 return, 실제 쿼리는 proxy 객체를 통해 최초로 데이터에 접근하는 시점에 실행
        // 이 메서드(getProduct) 안에서 실제 select 쿼리가 한 번만 실행됨
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setName(product.getName());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setStock(product.getStock());

        System.out.println(product.getClass().getName()); // com.springboot.api.data.entity.Product$HibernateProxy$RoNXS8yV

        System.out.println("ProductServiceImpl getProduct ends...");
        return productResponseDto;
    }

    @Override
    public ProductResponseDto saveProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());

        Product savedProduct = productDAO.insertProduct(product);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setName(savedProduct.getName());
        productResponseDto.setPrice(savedProduct.getPrice());
        productResponseDto.setStock(savedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public ProductResponseDto changeProductName(Long number, String name) throws Exception {
        Product changedProduct = productDAO.updateProductName(number, name);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setName(changedProduct.getName());
        productResponseDto.setPrice(changedProduct.getPrice());
        productResponseDto.setStock(changedProduct.getStock());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) throws Exception {
        productDAO.deleteProduct(number);
    }

}
