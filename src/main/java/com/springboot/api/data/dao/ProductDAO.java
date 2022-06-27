package com.springboot.api.data.dao;

import com.springboot.api.data.entity.Product;

public interface ProductDAO {

    public abstract Product insertProduct(Product product);

    public abstract Product selectProduct(Long number);

    public abstract Product updateProductName(Long number, String name) throws Exception;

    public abstract void deleteProduct(Long number) throws Exception;

}
