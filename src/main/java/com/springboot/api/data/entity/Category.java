package com.springboot.api.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    private String name;

    @OneToMany(fetch = FetchType.EAGER) // default는 LAZY
    @JoinColumn(name = "category_id")
    // 이렇게 선언하면 DB에 테이블 생성 시, Product 테이블에 "category_id" 컬럼명으로 FK가 생성된다.
    // 이 Category 테이블에는 id, code, name 세 개의 컬럼만 생성된다.
    private List<Product> products = new ArrayList<>();

}
