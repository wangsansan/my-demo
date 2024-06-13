package com.wang.demo.netty.web.request;

import com.wang.demo.netty.web.RpcMessage;
import lombok.Data;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 07:42
*/
@Data
public class RpcRequest implements RpcMessage {

    private int sequenceId;

    private String className;

    private String methodName;

    private Class<?> returnType;

    private Class<?>[] parameterTypes;

    private Object[] args;

    @Override
    public byte messageType() {
        return 88;
    }

    @Override
    public int sequenceId() {
        return sequenceId;
    }


}
