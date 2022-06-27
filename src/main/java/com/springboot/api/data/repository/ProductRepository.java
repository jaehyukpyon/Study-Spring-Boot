package com.springboot.api.data.repository;

import com.springboot.api.data.entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public abstract List<Product> findByName(String name, Sort sort);

    public abstract List<Product> findByNameOrderByNumberAsc(String name);

    public abstract List<Product> findByNameOrderByNumberDesc(String name);

    public abstract List<Product> findByNameOrderByPriceAscStockDesc(String name);

}
