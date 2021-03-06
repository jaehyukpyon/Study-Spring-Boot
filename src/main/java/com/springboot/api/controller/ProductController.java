package com.springboot.api.controller;

import com.springboot.api.data.dto.ChangeProductNameDto;
import com.springboot.api.data.dto.ProductDto;
import com.springboot.api.data.dto.ProductResponseDto;
import com.springboot.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(@Qualifier(value = "productServiceImpl2") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "")
    public ResponseEntity<ProductResponseDto> getProduct(Long number) {
        ProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).<ProductResponseDto>body(productResponseDto);
    }

    @PostMapping(value = "")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        ProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).<ProductResponseDto>body(productResponseDto);
    }

    @PutMapping(value = "")
    public ResponseEntity<ProductResponseDto> changeProductName(@RequestBody ChangeProductNameDto changeProductNameDto) throws Exception {
        ProductResponseDto productResponseDto = productService.changeProductName(changeProductNameDto.getNumber(), changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).<ProductResponseDto>body(productResponseDto);
    }

    @DeleteMapping(value = "")
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).<String>body("??????????????? ?????????????????????. >> number : " + number);
    }

}
