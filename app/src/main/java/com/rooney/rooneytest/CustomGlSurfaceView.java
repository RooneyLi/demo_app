package com.rooney.rooneytest;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class CustomGlSurfaceView extends GLSurfaceView implements GLSurfaceView.Renderer, View.OnClickListener {
    private Triangle mTriangle;

    public CustomGlSurfaceView(Context context) {
        this(context, null);
    }

    public CustomGlSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        initEGLEv();
    }

    private void initEGLEv() {
        setEGLContextClientVersion(2);
        setRenderer(this);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        mTriangle = new Triangle();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        GLES20.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
        mTriangle.draw();
        requestRender();
    }

    @Override
    public void onClick(View v) {
        MainActivity activity = (MainActivity) getContext();
        activity.commonJNI("测试测试测试");
        Log.e("Rooney",activity.getStringFromJNI());
    }
}
