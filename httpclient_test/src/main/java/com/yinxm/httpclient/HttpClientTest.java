package com.yinxm.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by H4X0R on 2016/3/10.
 */
public class HttpClientTest {
    public static  void main(String[] args) throws Exception{

        defaultClient();
    }

    public static void defaultClient() throws Exception {
        HttpClient client = new DefaultHttpClient();
        HttpGet get = new HttpGet("http://www.baidu.com");
        HttpResponse response = client.execute(get);
        HttpEntity entity = response.getEntity();
        System.out.println(EntityUtils.toString(entity));
    }

    public static void closeableClient() throws Exception {
//        CloseableHttpClient httpclient = HttpClients.createDefault();
//        try {
//            HttpGet httpGet = new HttpGet("http://httpbin.org/get");
//            CloseableHttpResponse response1 = httpclient.execute(httpGet);
//            // The underlying HTTP connection is still held by the response object
//            // to allow the response content to be streamed directly from the network socket.
//            // In order to ensure correct deallocation of system resources
//            // the user MUST call CloseableHttpResponse#close() from a finally clause.
//            // Please note that if response content is not fully consumed the underlying
//            // connection cannot be safely re-used and will be shut down and discarded
//            // by the connection manager.
//            try {
//                System.out.println(response1.getStatusLine());
//                HttpEntity entity1 = response1.getEntity();
//                // do something useful with the response body
//                // and ensure it is fully consumed
//                EntityUtils.consume(entity1);
//            } finally {
//                response1.close();
//            }
    }
}
