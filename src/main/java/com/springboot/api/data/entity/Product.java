package com.springboot.api.data.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "product")
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;


    @OneToOne(mappedBy = "product") // 이 필드는 데이터베이스 컬럼으로 생성 X
    @ToString.Exclude // ProductDetail 입장에서 이 entity를 참조한다. ProductDetail(FK) Product(PK)
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id") // foreign key references Provider (id)
    @ToString.Exclude // 이 entity 입장에서는 Provider를 참조한다.
    private Provider provider;

}
