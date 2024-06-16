package com.wang.demo.netty.register;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/16 16:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceAddress {

    private String host;

    private int port;

}
