package com.springboot.api.testpackage.repository;

import com.springboot.api.testpackage.entity.ProductDetailTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailTestRepository extends JpaRepository<ProductDetailTest, Long> {
}
