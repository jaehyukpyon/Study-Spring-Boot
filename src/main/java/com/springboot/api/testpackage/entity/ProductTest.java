package com.springboot.api.testpackage.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String productName;

    @OneToOne(mappedBy = "productTest")
    @ToString.Exclude
    private ProductDetailTest productDetailTest;

}
