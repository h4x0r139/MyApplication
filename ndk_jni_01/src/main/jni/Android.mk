LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := ndk_jni_01
LOCAL_SRC_FILES := ndk_jni_01c.c

include $(BUILD_SHARED_LIBRARY)