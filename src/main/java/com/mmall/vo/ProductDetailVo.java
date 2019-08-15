package com.mmall.vo;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * @program: mmall
 * @description: ProduvtDetailVo
 * @author: cxr
 * @create: 2019-08-09 17:23
 **/
@Data
@ToString
@Accessors(chain = true)
public class ProductDetailVo {
    private Integer id;
    private Integer categoryId;
    private String name;
    private String subtitle;
    private String mainImage;
    private String subImages;
    private String detail;
    private BigDecimal price;
    private Integer stock;
    private Integer status;
    private String createTime;
    private String updateTime;
    
    private String imageHost;
    
    private Integer parentCategoryId;
}
