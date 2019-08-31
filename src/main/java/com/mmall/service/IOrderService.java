package com.mmall.service;

import com.mmall.common.ServerResponse;

import java.util.Map;

/**
 * @program: mmall
 * @description: IOrderService
 * @author: cxr
 * @create: 2019-08-24 22:02
 **/
public interface IOrderService {
    ServerResponse pay(Long orderNo, Integer userId, String path);
    ServerResponse aliCallback(Map<String,String> params);
    ServerResponse queryOrderPayStatus(Integer userId,Long orderNo);
    ServerResponse createOrder(Integer userId,Integer shippingId);
}
