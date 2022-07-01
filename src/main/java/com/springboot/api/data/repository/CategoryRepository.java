package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    ;

}
