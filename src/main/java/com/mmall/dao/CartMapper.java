package com.mmall.dao;

import com.mmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
    
    Cart selectCartByUserIdProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);
    
    List<Cart> selectCartByUserId(Integer userId);
    
    int selectCartProductCheckedStatusByUserId(Integer userId);
}