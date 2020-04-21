package com.springcloud.zuul.common;

import lombok.Data;

@Data
public class Result<T> {
    private String code = "0";
    private String message = "ok";
    private T data;
}