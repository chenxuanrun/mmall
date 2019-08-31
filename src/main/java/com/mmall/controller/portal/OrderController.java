package com.mmall.controller.portal;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.demo.trade.config.Configs;
import com.google.common.collect.Maps;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @program: mmall
 * @description: OrderController
 * @author: cxr
 * @create: 2019-08-24 21:58
 **/
@RestController
@RequestMapping("order")
@Slf4j
public class OrderController {
    @Autowired
    IOrderService iOrderService;
    @RequestMapping("create.do")
    public ServerResponse create(HttpSession session,Integer shippingId){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        return iOrderService.pay(orderNo,user.getId(),path);
    }
    @RequestMapping("pay.do")
    public ServerResponse pay(HttpSession session, Long orderNo, HttpServletRequest request){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        String path=request.getSession().getServletContext().getRealPath("upload");
        return iOrderService.pay(orderNo,user.getId(),path);
    }       
    @RequestMapping("alipay_callback.do")
    public Object alipayCallback(HttpServletRequest request){
        Map<String,String> params= Maps.newHashMap();
       Map requestParams=request.getParameterMap(); 
       requestParams.forEach((key,value)->{
          String name=String.valueOf(key);
          String[] values= (String[]) value;
          String valusStr="";
          for (int i= 0;i<values.length;i++){
              valusStr = (i==values.length-1)?valusStr+values[i]:valusStr+values[i]+",";
          }
          params.put(name,valusStr);
       });
       log.info("支付宝回调,sign:{},trade_status:{},参数:{}",params.get("sign"),params.get("trade_status"),params.toString());
       //非常重要,验证回调的正确性,是不是支付宝发的,并且呢还要避免重复通知
       params.remove("sign_type");
        try {
            boolean alipayRSACheckedV2 = AlipaySignature.rsaCheckV2(params, Configs.getAlipayPublicKey(),"utf-8",Configs.getSignType());
            if (!alipayRSACheckedV2){
                return ServerResponse.createByErrorMessage("非法请求,验证不通过");
            }
        } catch (AlipayApiException e) {
           log.error("支付宝验证回调异常",e);
        }
        //todo 验证各种数据
        
        //
        ServerResponse serverResponse=iOrderService.aliCallback(params);
        return serverResponse.isSuccess()?Const.AlipayCallback.RESPONSE_SUCCESS:Const.AlipayCallback.RESPONSE_FAILED;
    }

    @RequestMapping("query_order_pay_status.do")
    public ServerResponse<Boolean> queryOrderPayStatus(HttpSession session, Long orderNo){
        User user= (User) session.getAttribute(Const.CURRENT_USER);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),ResponseCode.NEED_LOGIN.getDesc());
        }
        ServerResponse<Boolean> serverResponse=iOrderService.queryOrderPayStatus(user.getId(),orderNo);
        return serverResponse.isSuccess()?ServerResponse.createBySuccess(true):ServerResponse.createBySuccess(false);
    }
}
