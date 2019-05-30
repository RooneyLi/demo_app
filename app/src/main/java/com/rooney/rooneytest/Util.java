package com.rooney.rooneytest;

import android.opengl.GLES20;

public class Util {
    public static int loadShader (int type, String shaderCode) {
        // 创建顶点着色器类型(GLES20.GL_VERTEX_SHADER)
        // 创建片元着色器类型(GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);
        // 着色器源码编译,并完成
        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);
        return shader;
    }

}
