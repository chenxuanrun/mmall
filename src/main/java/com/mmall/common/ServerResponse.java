package com.mmall.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
@JsonInclude(JsonInclude.Include.NON_NULL) //该注解可以用在类和属性上 字段为空则json序列化之后不保留该字段
public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;
    
    private ServerResponse(int status){
        this.status=status;
    }
    
    private ServerResponse(int status,T data){
        this.status=status;
        this.data=data;
    }

    private ServerResponse(int status,String msg){
        this.status=status;
        this.msg=msg;
    }
    private ServerResponse(int status,String msg,T data){
        this.status=status;
        this.msg=msg;
        this.data=data;
    }
    
    @JsonIgnore  //不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
    
    public static <T> ServerResponse<T> createBySuccess(){
      return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),ResponseCode.SUCCESS.getDesc());  
    }
    //
    public static <T> ServerResponse<T> createBySuccessMessage(String message){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message);
    }
    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }
    //
    public static <T> ServerResponse<T> createBySuccess(String message,T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),message,data);
    }
    
    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    
    public static <T>  ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
}
