package com.wang.demo.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/7/14 8:45 上午
 */
public class CollectionUtil {

    public static <T, R> List<R> mapList(Collection<T> source, Function<T, R> function) {
        return CollectionUtils.emptyIfNull(source)
                .stream()
                .map(function)
                .collect(Collectors.toList());
    }

    public static <T> String mapString(Collection<T> source) {
        return mapString(source, String::valueOf);
    }

    public static <T> String mapString(Collection<T> source, Function<T, String> function) {
        return CollectionUtils.emptyIfNull(source)
                .stream()
                .map(function)
                .collect(Collectors.joining(","));
    }

}
