package com.springboot.api.service;

import com.springboot.api.data.dto.ProductDto;
import com.springboot.api.data.dto.ProductResponseDto;

public interface ProductService {

    public abstract ProductResponseDto getProduct(Long number);

    public abstract ProductResponseDto saveProduct(ProductDto productDto);

    public abstract ProductResponseDto changeProductName(Long number, String name) throws Exception;

    public abstract void deleteProduct(Long number) throws Exception;

}
