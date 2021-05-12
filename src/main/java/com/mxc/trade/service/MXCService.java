package com.mxc.trade.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mxc.trade.utils.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.plexus.util.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class MXCService {


    @Value("${app.api.url}")
    private String apiUrl;

    public BigDecimal getPrice(String coin) {

        if (StringUtils.isBlank(coin)) {
            log.info("coin not null");
            return null;
        }

        try {
            JSONObject json = JSON.parseObject(HttpUtils.doGet(apiUrl + "open/api/v2/market/ticker?symbol=" + coin));
            return json.getJSONArray("data").getJSONObject(0).getBigDecimal("last");
        } catch (Exception e) {
            log.error("Exception e ");
            return null;
        }
    }

    public static void main(String[] args) {
        String resp = HttpUtils.doGet("https://www.mxc.com/open/api/v2/market/ticker?symbol=ETH_USDT");
        JSONObject json = JSON.parseObject(resp);
        System.out.println(json.getJSONArray("data").getJSONObject(0).getBigDecimal("last"));
    }
}
