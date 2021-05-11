package com.mxc.trade.controller;


import com.alibaba.fastjson.JSON;
import com.mxc.trade.model.TradeKey;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(tags = "API KEY 管理")
@RestController
@RequestMapping("/trade")
public class KeyManageController {

    private TradeKey tradeKey;

    @ApiOperation("設定API 金鑰")
    @PostMapping("/set/key")
    public void setKey(@RequestBody TradeKey inputParam) {
        this.tradeKey = inputParam;
        log.info("設定API 金鑰成功 tradeKey:{} ", JSON.toJSON(tradeKey));
    }

}
