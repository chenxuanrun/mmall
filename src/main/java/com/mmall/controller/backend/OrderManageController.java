package com.mmall.controller.backend;

import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisShardedPoolUtil;
import com.mmall.vo.OrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @program: mmall
 * @description: OrderManageController
 * @author: cxr
 * @create: 2019-09-07 20:54
 **/
@RestController
@RequestMapping("/manage/order")
public class OrderManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IOrderService iOrderService;
    @RequestMapping("list.do")
    public ServerResponse<PageInfo> orderList(HttpServletRequest request,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        String loginToken= CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员,填充逻辑
            return iOrderService.manageList(pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
    @RequestMapping("detail.do")
    public ServerResponse<OrderVo> orderDetail(HttpServletRequest request, Long orderNo){
        String loginToken=CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员,填充逻辑
            return iOrderService.manageDetail(orderNo);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
    @RequestMapping("search.do")
    public ServerResponse<PageInfo> orderSearch(HttpServletRequest request, Long orderNo,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,@RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        String loginToken=CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员,填充逻辑
            return iOrderService.manageSearch(orderNo,pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }

    @RequestMapping("send_goods.do")
    public ServerResponse<String> orderSendGoods(HttpServletRequest request, Long orderNo){
        String loginToken=CookieUtil.readLoginToken(request);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        String userJsonStr = RedisShardedPoolUtil.get(loginToken);
        User user = JsonUtil.string2Obj(userJsonStr, User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录,请登录");
        }
        //校验一下是否是管理员
        if (iUserService.checkAdminRole(user).isSuccess()){
            //是管理员,填充逻辑
            return iOrderService.manageSendGoods(orderNo);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作,需要管理员权限");
        }
    }
}
