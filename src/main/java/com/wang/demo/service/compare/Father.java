package com.wang.demo.service.compare;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author: Wangchunsheng
 * @Date: 2022/4/13 6:48 下午
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Father {

    String name;

    List<Son> sonList;
}
