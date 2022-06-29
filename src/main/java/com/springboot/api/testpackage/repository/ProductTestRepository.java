package com.springboot.api.testpackage.repository;

import com.springboot.api.testpackage.entity.ProductTest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductTestRepository extends JpaRepository<ProductTest, Long> {

    ;

}
