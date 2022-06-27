package com.springboot.api.controller;

import com.springboot.api.data.dto.ProductResponseDto;
import com.springboot.api.service.impl.ProductServiceImpl2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceImpl2 productService;

    //@Test
    //@DisplayName(value = "MockMvc를 통한 Product 데이터 가져오기 테스트")
    public void getProductTest() throws Exception {

        given(productService.getProduct(123L)).willReturn(new ProductResponseDto(123L, "pen", 5_000, 5_000));

        String productId = "123";


    }

}