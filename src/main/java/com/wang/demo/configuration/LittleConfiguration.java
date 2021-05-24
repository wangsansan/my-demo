package com.wang.demo.configuration;

import com.wang.demo.service.Little.LittleService;
import com.wang.demo.service.Little.PrimaryLittleServiceImpl;
import com.wang.demo.service.Little.SecondLittleServiceImpl;
import com.wang.demo.service.request.MyRequest;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/23 7:24 下午
 */
@Configuration
@ConditionalOnProperty(value = "little.use")
public class LittleConfiguration {

    @Resource
    private MyRequest myRequest;

    @Bean
    @ConditionalOnProperty(value = "little.use", havingValue = "1")
    public LittleService primaryLittleService() {
        PrimaryLittleServiceImpl primaryLittleService = new PrimaryLittleServiceImpl();
        primaryLittleService.setMyRequest(myRequest);
        return primaryLittleService;
    }

    @Bean
    @ConditionalOnProperty(value = "little.use", havingValue = "2")
    public LittleService sendLittleService() {
        SecondLittleServiceImpl secondLittleService  = new SecondLittleServiceImpl();
        secondLittleService.setMyRequest(myRequest);
        return secondLittleService;
    }

}
