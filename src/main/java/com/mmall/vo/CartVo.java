package com.mmall.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mmall
 * @description: CartVo
 * @author: cxr
 * @create: 2019-08-15 12:15
 **/
@Data
@ToString
public class CartVo {
    List<CartProductVo> cartProductVoList;
    private BigDecimal cartTotalPrice;
    private Boolean allChecked;//是否已经都勾选
    private String imageHost;
    
}
