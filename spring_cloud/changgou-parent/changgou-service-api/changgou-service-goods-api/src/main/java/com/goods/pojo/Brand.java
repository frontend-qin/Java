package com.goods.pojo;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name ="tb_brand")
@Data
public class Brand {
    @Id
    private Long id;
    private String name;
    private String image;
    private String letter;
    private Integer seq;
}
