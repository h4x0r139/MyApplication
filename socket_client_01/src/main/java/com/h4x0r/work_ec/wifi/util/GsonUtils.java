package com.h4x0r.work_ec.wifi.util;

import com.google.gson.Gson;

/**
 * Created by yinxm on 2016/8/2.
 */

public class GsonUtils<T> {
    public String toJson(Object mJsonElement) {
        try {
            Gson gson = new Gson();
            String json = gson.toJson(mJsonElement);
            return json;
        } catch (Exception e) {
            return "";
        }
    }

    public T fromJson(String json, Class<T> classOfT) {
        try {
            Gson gson = new Gson();
            T clazz = (T) gson.fromJson(json, classOfT);
            return clazz;
        } catch (Exception e) {
            return null;
        }
    }

}
