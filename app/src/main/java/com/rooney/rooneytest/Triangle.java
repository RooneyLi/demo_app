package com.rooney.rooneytest;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Triangle {
    static final int COORDS_VERTEX = 3;
    static float triangleCoords[] = {  // 逆时针顺序
            0.0f,  0.622008459f, 0.0f, // 顶部顶点
            -0.5f, -0.311004243f, 0.0f, // 左下
            0.5f, -0.311004243f, 0.0f  // 右下
    };
    private final int mProgram;
    private final FloatBuffer vertexBuffer;

    // 设置图形的颜色值，RGBA四项
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
    private final int vertexCount = triangleCoords.length / COORDS_VERTEX;
    private final int vertexStride = COORDS_VERTEX * 4;

    //顶点着色器
    private final String vertexShaderCode =
            "attribute vec4 vPosition;              \n" +
                    "void main() {                  \n" +
                    "  gl_Position = vPosition;     \n" +
                    "}";
    //片元着色器
    private final String fragmentShaderCode =
            "precision mediump float;               \n" +
                    "uniform vec4 vColor;           \n" +
                    "void main() {                  \n" +
                    "  gl_FragColor = vColor;       \n" +
                    "}";
    private int mPositonHander;
    private int mColorHandle;

    public Triangle() {
        //初始化顶点坐标的字节流
        ByteBuffer bb = ByteBuffer.allocateDirect(
                // float有4个字节
                triangleCoords.length * 4);
        //使用与设备相关的字节序
        bb.order(ByteOrder.nativeOrder());
        //创建字节流
        vertexBuffer = bb.asFloatBuffer();
        //顶点坐标数据添加进字节流中
        vertexBuffer.put(triangleCoords);
        //设置开始读取位置
        vertexBuffer.position(0);

        //着色器编译
        int vertexShader = Util.loadShader(GLES20.GL_VERTEX_SHADER,vertexShaderCode);
        int fragmentShader = Util.loadShader(GLES20.GL_FRAGMENT_SHADER,fragmentShaderCode);
        //创建OpenGL Program对象
        mProgram = GLES20.glCreateProgram();
        //着色器和program进行绑定
        GLES20.glAttachShader(mProgram,vertexShader);
        GLES20.glAttachShader(mProgram,fragmentShader);
        GLES20.glLinkProgram(mProgram);
    }

    public void draw () {
        // 添加program到OpenGL ES环境
        GLES20.glUseProgram(mProgram);
        //获取顶点着色器中的vPosition对象
        mPositonHander = GLES20.glGetAttribLocation(mProgram,"vPosition");
        //设置mPositionHandle可用，用于传递顶点坐标数据
        GLES20.glEnableVertexAttribArray(mPositonHander);
        //准备三角形坐标数据
        GLES20.glVertexAttribPointer(mPositonHander,COORDS_VERTEX,GLES20.GL_FLOAT,false,vertexStride, vertexBuffer);
        //获取片元着色器中的vColor对象
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        //设置绘制三角形的颜色
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);
        //正式绘制三角形
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        //绘制完成后顶点属性设置不可用
        GLES20.glDisableVertexAttribArray(mPositonHander);
    }


}
