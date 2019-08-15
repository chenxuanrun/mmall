package com.mmall.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @program: mmall
 * @description: ProductListVo
 * @author: cxr
 * @create: 2019-08-12 16:07
 **/
@Data
@ToString
@Accessors(chain = true)
public class ProductListVo {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private BigDecimal price;
    private Integer status;
    private String imageHost;

}
