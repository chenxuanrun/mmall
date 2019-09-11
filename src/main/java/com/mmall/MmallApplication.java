package com.mmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.mmall.dao") // mybatis扫描路径，针对的是接口Mapper类
public class MmallApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MmallApplication.class, args);
    }
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(MmallApplication.class);
    }
}
