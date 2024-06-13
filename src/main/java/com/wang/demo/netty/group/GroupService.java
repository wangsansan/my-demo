package com.wang.demo.netty.group;

import com.google.common.collect.Maps;
import com.wang.demo.netty.session.SessionService;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/11 20:15
 */
public class GroupService {

    public static final Map<String, List<String>> groupInfo = Maps.newHashMap();

    public boolean newGroup(String groupName, List<String> userNameList) {
        if (groupInfo.containsKey(groupName)) {
            return false;
        }
        groupInfo.put(groupName, userNameList);
        return true;
    }

    public void sendMsg(String msg, String group) {
        if (!groupInfo.containsKey(group)) {
            return;
        }

        Map<String, NioSocketChannel> userChannelMap = SessionService.USER_CHANNEL_MAP;
        for (String s : groupInfo.get(group)) {
            NioSocketChannel channel = userChannelMap.get(s);
            if (Objects.nonNull(channel)) {
                channel.writeAndFlush(msg);
            }
        }
    }

}
