package com.wang.demo.netty.register;

import com.fasterxml.jackson.core.type.TypeReference;
import com.wang.demo.utils.JsonUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: Wangchunsheng
 * @Date: 2024/6/16 16:49
 */
@Slf4j
public class LocalServiceRegistry implements ServiceRegistry {

    private static Map<String, Set<ServiceAddress>> centerAddressMap = new ConcurrentHashMap<>();

    private static Path path = Paths.get("/tmp/register.txt");

    @SneakyThrows
    @Override
    public void registerService(String host, int port, List<String> serviceKeyList) {
        log.info("before, address center:[{}]", JsonUtils.writeToString(centerAddressMap));
        for (String serviceKey : serviceKeyList) {
            centerAddressMap.computeIfAbsent(serviceKey, key -> new HashSet<>()).add(new ServiceAddress(host, port));
        }
        Files.write(path, JsonUtils.writeToString(centerAddressMap).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        log.info("after, address center:[{}]", JsonUtils.writeToString(centerAddressMap));
    }

    @SneakyThrows
    @Override
    public void unregisterService(List<String> serviceKeyList, String host, int port) {
        for (String s : CollectionUtils.emptyIfNull(serviceKeyList)) {
            Set<ServiceAddress> addressSet = centerAddressMap.getOrDefault(s, Collections.emptySet());
            addressSet.remove(new ServiceAddress(host, port));
            if (CollectionUtils.isEmpty(addressSet)) {
                centerAddressMap.remove(s);
            }
        }
        Files.write(path, JsonUtils.writeToString(centerAddressMap).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

    @SneakyThrows
    @Override
    public List<ServiceAddress> findServiceAddress(String serviceKey) {
        if (MapUtils.isEmpty(centerAddressMap)) {
            synchronized (LocalServiceRegistry.class) {
                if (MapUtils.isEmpty(centerAddressMap)) {
                    byte[] bytes = Files.readAllBytes(path);
                    centerAddressMap = JsonUtils.parse(new String(bytes), new TypeReference<ConcurrentHashMap<String, Set<ServiceAddress>>>() {
                    });
                }
            }
        }
        Set<ServiceAddress> addressSet = centerAddressMap.getOrDefault(serviceKey, Collections.emptySet());
        return new ArrayList<>(addressSet);
    }
}
