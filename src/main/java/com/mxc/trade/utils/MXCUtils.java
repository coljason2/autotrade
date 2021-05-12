package com.mxc.trade.utils;

import com.mxc.trade.model.SignVo;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.collections4.MapUtils;
import org.codehaus.plexus.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class MXCUtils {
    /**
     * Gets the get request parameter string
     *
     * @param param get/delete Request parameters map
     * @return
     */
    public static String getRequestParamString(Map<String, String> param) {
        if (MapUtils.isEmpty(param)) {
            return "";
        }
        StringBuilder sb = new StringBuilder(1024);
        SortedMap<String, String> map = new TreeMap<>(param);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = StringUtils.isBlank(entry.getValue()) ? "" : entry.getValue();
            sb.append(key).append('=').append(urlEncode(value)).append('&');
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static String urlEncode(String s) {
        try {
            return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("UTF-8 encoding not supported!");
        }
    }

    /**
     * signature
     */
    public static String sign(SignVo signVo) {
        if (signVo.getRequestParam() == null) {
            signVo.setRequestParam("");
        }
        String str = signVo.getAccessKey() + signVo.getReqTime() + signVo.getRequestParam();
        return actualSignature(str, signVo.getSecretKey());
    }

    public static String actualSignature(String inputStr, String key) {
        Mac hmacSha256;
        try {
            hmacSha256 = Mac.getInstance("HmacSHA256");
            SecretKeySpec secKey =
                    new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            hmacSha256.init(secKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No such algorithm: " + e.getMessage());
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Invalid key: " + e.getMessage());
        }
        byte[] hash = hmacSha256.doFinal(inputStr.getBytes(StandardCharsets.UTF_8));
        return Hex.encodeHexString(hash);
    }

}
