package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.vo.CartVo;

/**
 * @program: mmall
 * @description: ICartService
 * @author: cxr
 * @create: 2019-08-15 11:40
 **/
public interface ICartService {
    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
}
