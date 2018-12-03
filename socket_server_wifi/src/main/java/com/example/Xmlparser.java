package com.example;

/**
 * Created by yinxm on 2016/8/2.
 */

public class Xmlparser {

    public static void main(String[] args){
        String xml = "<WORK_EC>{\"action\":\"TEST\",\"requestCode\":\"c16222c8-d766-4965-a2e3-665ef06aa81f\",\"requestFrom\":\"4\",\"requestTo\":\"0\"}</WORK_EC>";
        String substring = xml.substring("<WORK_EC>".length(), xml.indexOf("</WORK_EC>"));
        System.out.println(substring);
    }
}
