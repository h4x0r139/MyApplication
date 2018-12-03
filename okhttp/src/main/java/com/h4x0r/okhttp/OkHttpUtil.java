package com.h4x0r.okhttp;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yinxm on 2016/4/1.
 */
public class OkHttpUtil {


    private static final OkHttpClient client = new OkHttpClient();
    private static Response response;


    /**
     * 原生okhttp3 get 请求 【适用于get返回数据量比较大】
     * @param url
     * @param params
     * @return response
     * @throws IOException
     */
    public static Response getFormResponse(String url, Map<String, String> params) throws IOException {
        if (StringUtil.isBlank(url)) {
            return null;
        }

        StringBuilder sb = new StringBuilder(url);

        if (params != null && !params.isEmpty()) {
            if (url.contains("?")) {
                sb.append("&");
            } else {
                sb.append("?");
            }
            Set<Map.Entry<String, String>> set = params.entrySet();
            for (Map.Entry<String, String> entry : set) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            sb = sb.deleteCharAt(sb.length() - 1);
        }
        url = sb.toString();
        LogUtil.i("get url="+url);

        Request request = new Request.Builder()
                .url(url)
                .build();
        return client.newCall(request).execute();

    }


    /**
     * 原生okhttp3 get 请求
     * @param url
     * @param params
     * @return
     */
    public static String getForm(String url, Map<String, String> params) {

        try {
            Response response = getFormResponse(url, params);
            if (response != null && response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";

    }


    /**
     * 原生okhttp post form 表单 同步提交
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String postForm(String url, Map<String, String> params) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            Set<Map.Entry<String, String>> set = params.entrySet();
            for (Map.Entry<String, String> entry : set) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("Unexpected code " + response);
        }
        String str = response.body().string();
        LogUtil.d("postForm="+str);
        return  str;
    }

    public static String postForm2() throws IOException {
        OkHttpClient client = new OkHttpClient();
         RequestBody formBody = new FormBody.Builder()
                    .build();
            Request request = new Request.Builder()
                    .url("http://www.baidu.com")
                    .post(formBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
        return response.body().string();
    }


    /**
     * okhttp post form 表单 异步提交，在子线线程执行回调
     * @param url
     * @param parms
     * @param callback 在子线线程执行回调在子线线程执行回调
     * @throws IOException
     */
    public static void postForm_AsyncSubThread(String url, Map<String, String> parms,
                                               okhttp3.Callback callback) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        Set<Map.Entry<String, String>> set = parms.entrySet();
        for (Map.Entry<String, String> entry : set) {
            builder.add(entry.getKey(), entry.getValue());
        }
        FormBody formBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)

                .build();

        client.newCall(request).enqueue(callback);
    }
}
