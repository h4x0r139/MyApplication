//
// Created by work_ec on 2016/10/30.
//
#include "cn_yinxm_ndk_MainActivity.h"
   JNIEXPORT jstring JNICALL Java_cn_yinxm_ndk_MainActivity_getStrFromJni
     (JNIEnv * env, jobject obj) {
           char* cstr = "yinxm test jni String ";
           return (*env)->NewStringUTF(env, cstr);
     }