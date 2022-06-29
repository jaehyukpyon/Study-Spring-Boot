package com.springboot.api.data.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "provider")
public class Provider extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    
    // @OneToMany의 default fetch 전략은 LAZY
    // mappedBy로 설정된 필드는 컬럼에 적용되지 않음
    @OneToMany(mappedBy = "provider", fetch = FetchType.EAGER)
    @ToString.Exclude // 이 엔티티가 Product 테이블에 여러 개 존재할 수 있음
    private List<Product> productList = new ArrayList<>();

}
