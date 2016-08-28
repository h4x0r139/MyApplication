package com.example;

/**
 * Created by yinxm on 2016/8/2.
 */

public class Xmlparser {

    public static void main(String[] args){
        String xml = "<ECARX>{\"action\":\"TEST\",\"requestCode\":\"c16222c8-d766-4965-a2e3-665ef06aa81f\",\"requestFrom\":\"4\",\"requestTo\":\"0\"}</ECARX>";
        String substring = xml.substring("<ECARX>".length(), xml.indexOf("</ECARX>"));
        System.out.println(substring);
    }
}
