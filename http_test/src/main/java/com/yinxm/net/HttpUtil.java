package com.yinxm.net;

import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;

/**
 * Created by H4X0R on 2016/3/9.
 */
public class HttpUtil {

    /**
     *
     * @param params map
     * @param encode
     * @return userName=test&pwd=test
     */
    public static StringBuilder getRequestData(Map<String, String> params, String encode) {
        StringBuilder stringBuilder = new StringBuilder();        //存储封装好的请求体信息
        try {
            Set<Map.Entry<String,String>> set = params.entrySet();
            for (Map.Entry<String,String> entry : set) {
                stringBuilder.append(entry.getKey()).append("=");
                stringBuilder.append(URLEncoder.encode(entry.getValue(), encode)).append("&");            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder;
    }
}
