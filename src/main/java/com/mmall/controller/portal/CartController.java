package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.ICartService;
import com.mmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * @program: mmall
 * @description: CartController
 * @author: cxr
 * @create: 2019-08-15 11:29
 **/
@RequestMapping("cart")
@RestController
public class CartController {
    @Autowired
    ICartService iCartService;
    
    public ServerResponse<CartVo> add(HttpSession session, Integer count, Integer productId){ 
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
             return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iCartService.add(user.getId(),productId,count);
    }
}
