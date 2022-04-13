package com.wang.demo.service.compare;

import lombok.*;

import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/4/13 6:48 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Son {

    String name;

    List<Toy> toyList;

}
