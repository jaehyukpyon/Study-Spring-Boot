package com.springboot.api.data.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChangeProductNameDto {

    private Long number;

    private String name;

}
