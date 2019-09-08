package com.mmall.vo;

import com.mmall.pojo.OrderItem;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mmall
 * @description: OrderProductVo
 * @author: cxr
 * @create: 2019-09-07 19:03
 **/
@Data
@ToString
public class OrderProductVo {
    private List<OrderItemVo> orderItemVoList;
    private BigDecimal productTotalPrice;
    private String imageHost;
}
