package com.wang.demo.netty.web;

import com.wang.demo.netty.web.request.RpcRequest;
import com.wang.demo.netty.web.response.RpcResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 07:40
*/
public interface RpcMessage {

    int MAGIC_NUM = 1234;

    int VERSION = 1;
    // 1 是 json
    byte SERIALIZE_TYPE = 1;

    Map<Byte, Class<? extends RpcMessage>> classMap = new HashMap<Byte, Class<? extends RpcMessage>>() {
        {
            put((byte) 88, RpcRequest.class);
            put((byte) 99, RpcResponse.class);
        }
    };

    byte messageType();

    int sequenceId();

}
