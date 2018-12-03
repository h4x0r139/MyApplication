//
// Created by work_ec on 2016/12/29.
//
#include "cn_yinxm_ndk_KeyUtil.h"
/*
 * Class:     cn_yinxm_ndk_KeyUtil
 * Method:    getKey
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_cn_yinxm_ndk_KeyUtil_getKey
  (JNIEnv * env, jclass obj){
    char* str = "==this is key";
    return (*env)->NewStringUTF(env, str);
  }
JNIEXPORT jstring JNICALL Java_cn_yinxm_ndk_KeyUtil_callJavaStaticMethod
  (JNIEnv *, jclass) {


  }

/*
 * Class:     cn_yinxm_ndk_KeyUtil
 * Method:    callJavaObjectMethod
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_cn_yinxm_ndk_KeyUtil_callJavaObjectMethod
  (JNIEnv *, jclass){

  }