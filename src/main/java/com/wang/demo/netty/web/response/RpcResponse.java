package com.wang.demo.netty.web.response;

import com.wang.demo.netty.web.RpcMessage;
import lombok.Data;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/13 07:43
*/
@Data
public class RpcResponse implements RpcMessage {

    private int sequenceId;

    private int code;

    private Object result;

    private Throwable cause;

    @Override
    public byte messageType() {
        return 99;
    }

    @Override
    public int sequenceId() {
        return sequenceId;
    }
}
