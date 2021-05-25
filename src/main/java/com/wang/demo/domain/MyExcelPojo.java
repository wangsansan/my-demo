package com.wang.demo.domain;

/**
 * @Author: Wangchunsheng
 * @Date: 2021/5/25 8:42 下午
 */
public class MyExcelPojo {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyExcelPojo{" +
                "id=" + id +
                '}';
    }
}
