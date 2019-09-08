package com.mmall.vo;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @program: mmall
 * @description: OrderItemVo
 * @author: cxr
 * @create: 2019-09-07 18:21
 **/
@Data
@ToString
public class OrderItemVo {
    private Long orderNo;

    private Integer productId;
    
    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private String createTime;
}
