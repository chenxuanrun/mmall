package com.mmall.vo;

import com.mmall.pojo.Shipping;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * @program: mmall
 * @description: OrderVo
 * @author: cxr
 * @create: 2019-09-07 18:18
 **/
@Data
@ToString
public class OrderVo {
    private Long orderNo;
    
    private BigDecimal payment;
    
    private Integer paymentType;
    
    private String paymentTypeDesc;
    
    private Integer postage;
    
    private Integer status;
    
    private String statusDesc;
    
    private String paymentTime;

    private String sendTime;

    private String endTime;

    private String closeTime;

    private String createTime;
    
    //订单的明细
    private List<OrderItemVo> orderItemVoList;
    
    private String imageHost;
    
    private Integer shippingId;
    
    private String receiverName;
    
    private ShippingVo shippingVo;
}
