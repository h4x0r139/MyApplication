package cn.yinxm.util.time;

/**
 * Created by yinxm on 2017/3/28.
 * 功能:
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 读取网络时间
 *
 * @author SHANHY(365384722@QQ.COM)
 * @date 2015年11月27日
 */
public class GetNetworkTime {

    public static void main(String[] args) {
        String webUrl1 = "http://www.bjtime.cn";//bjTime  时间有问题
        String webUrl2 = "http://www.baidu.com";//百度
        String webUrl3 = "http://www.taobao.com";//淘宝
        String webUrl4 = "http://www.ntsc.ac.cn";//中国科学院国家授时中心 时间有问题
        String webUrl5 = "http://www.360.cn";//360
        String webUrl6 = "http://www.beijing-time.org";//beijing-time
        System.out.println(getWebsiteDatetime(webUrl2) + " [百度]");
        System.out.println(getWebsiteDatetime(webUrl3) + " [淘宝]");
        System.out.println(getWebsiteDatetime(webUrl4) + " [中国科学院国家授时中心]");
        System.out.println(getWebsiteDatetime(webUrl5) + " [360安全卫士]");
        System.out.println(getWebsiteDatetime(webUrl6) + " [beijing-time]");
        System.out.println(getWebsiteDatetime(webUrl1) + " [bjtime.cn]");

        List<Long> list = new ArrayList<>();
        try {
            long time = getWebsiteDatetimeLong(webUrl2);
            if (time > 0) {
                list.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            long time = getWebsiteDatetimeLong(webUrl3);
            if (time > 0) {
                list.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            long time = getWebsiteDatetimeLong(webUrl4);
            if (time > 0) {
                list.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            long time = getWebsiteDatetimeLong(webUrl5);
            if (time > 0) {
                list.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            long time = getWebsiteDatetimeLong(webUrl6);
            if (time > 0) {
                list.add(time);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        System.err.println("list="+list);
        Collections.sort(list);
        System.err.println("list2="+list);
        if (list.size() > 2) {
            list.remove(0);
            list.remove(list.size()-1);
        }

    }

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author SHANHY
     * @date 2015年11月27日
     */
    private static String getWebsiteDatetime(String webUrl) {
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            long ld = uc.getDate();// 读取网站日期时间
            Date date = new Date(ld);// 转换为标准时间对象
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);// 输出北京时间
            return sdf.format(date);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定网站的日期时间
     *
     * @param webUrl
     * @return
     * @author SHANHY
     * @date 2015年11月27日
     */
    private static long getWebsiteDatetimeLong(String webUrl) {
        long time = 0;
        try {
            URL url = new URL(webUrl);// 取得资源对象
            URLConnection uc = url.openConnection();// 生成连接对象
            uc.connect();// 发出连接
            time = uc.getDate();// 读取网站日期时间
            System.err.println("ld=" + time + ", " + System.currentTimeMillis());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return time;
    }
}