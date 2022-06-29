package com.springboot.api.testpackage.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductDetailTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productDetailId;

    private String productDetailDescription;

    @OneToOne
    @JoinColumn(name = "product_test_fk")
    private ProductTest productTest;

}
