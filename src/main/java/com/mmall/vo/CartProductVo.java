package com.mmall.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @program: mmall
 * @description: 结合了产品和购物车的一个抽象对象
 * @author: cxr
 * @create: 2019-08-15 12:09
 **/
@Data
@ToString
public class CartProductVo {
    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer quantity;//购物车中次商品的数量
    private String productName;
    private String productSubtitle;
    private String productMainImage;
    private BigDecimal productPrice;
    private Integer productStatus;
    private BigDecimal productTotalPrice;
    private Integer productStock;//产品库存
    private Integer productChecked;//此商品是否勾选
    private String limitQuantity;//限制数量的一个返回结果
    
}
