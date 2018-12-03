package com.h4x0r.okhttp;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by yinxm on 2016/3/22.
 */
public class OkHttpUtils {
    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static void fileUpload(String urlStr, File file)  throws IOException{
            Request request = new Request.Builder()
                    .url(urlStr)
                    .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
                    .build();
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            System.out.println(response.body().string());
    }
    public static void main(String[] args) throws IOException {
        OkHttpUtils.fileUpload("",new File("G:/test_upload.doc"));
    }
}
