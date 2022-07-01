package com.springboot.api.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Table(name = "product_detail")
@Entity
public class ProductDetail extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;


    @OneToOne //(optional = false) product가 null인 값을 허용하지 않음
    @JoinColumn(name = "product_number") // foreign key references Product (number), ProductDetail이 Product을 참조한다.
    private Product product;

}
