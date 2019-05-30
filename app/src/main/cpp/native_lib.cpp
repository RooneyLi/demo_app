//
// Created by lilongfei on 19-5-7.
//
#include <jni.h>
#include <android/log.h>
#include <stdio.h>
#include <cstring>
#include "object_class.h"


#define TAG "Rooney_JNI"
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__);
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__);
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__);
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__);
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__);


extern "C" {
JNIEXPORT void JNICALL
Java_com_rooney_rooneytest_MainActivity_commonJNI(JNIEnv *env, jobject thiz, jstring str) {
    const char *dst = env->GetStringUTFChars(str,NULL);
    char *src = "_hell";
    char *res_str = strcat(const_cast<char *>(dst), src);
    char *sArray[2][64] = {
            {res_str},
            {res_str}
    };
    char *fileName = *sArray[1];
    LOGE("--->%s",fileName);
//    FILE *file = fopen("/sdcard/DCIM/Camera","r");
//    fprintf(file, "%s %s %s %d", "We", "are", "in", 2014);
    create_new_struct();
}

JNIEXPORT jstring JNICALL
Java_com_rooney_rooneytest_MainActivity_getStringFromJNI(JNIEnv *env, jobject thiz) {
    return env->NewStringUTF("get_string_from_jin");
}

}


struct Box_struct {
    double height;
    double breadth;
};

void create_new_struct(){
    struct Box_struct box_strutc1;
    box_strutc1.height=30;
    struct Box_struct *box_struct;
    box_struct = &box_strutc1;
    box_struct->height=20.0;
    LOGE("C++-->height:%f",box_struct->height)
}


void create_struct() {
    struct Box_struct box_strutc;
    box_strutc.height=30;
    LOGE("C++-->height:%f",box_strutc.height)
}

void create_new_object() {
    Box *box1 = new Box();
    box1->height = 2.0;
    LOGE("C++-->height:%f",box1->height)
}

// 非new创建的对象
void createObject() {
    Box box1;
    box1.breadth = 1.0;
    box1.height = 2.0;
    LOGE("C++-->breadth:%f",box1.breadth)
}

