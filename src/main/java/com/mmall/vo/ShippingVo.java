package com.mmall.vo;

import lombok.Data;
import lombok.ToString;

/**
 * @program: mmall
 * @description: ShippingVo
 * @author: cxr
 * @create: 2019-09-07 18:25
 **/
@Data
@ToString
public class ShippingVo {
    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;
}
