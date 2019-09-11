package com.mmall.conf;

import com.mmall.MmallApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @program: mmall
 * @description: ServletInitializer
 * @author: cxr
 * @create: 2019-09-11 11:57
 **/
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder)
    {
        return builder.sources(MmallApplication.class);
    }
}
