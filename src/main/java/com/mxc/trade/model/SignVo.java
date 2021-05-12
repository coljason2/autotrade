package com.mxc.trade.model;

import lombok.Data;

@Data
public class SignVo {
    private String reqTime;
    private String accessKey;
    private String secretKey;
    private String requestParam;
}
