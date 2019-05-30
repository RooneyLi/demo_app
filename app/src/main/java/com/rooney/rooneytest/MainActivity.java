package com.rooney.rooneytest;

import android.hardware.camera2.CaptureRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity {

    static{
        System.loadLibrary("native-lib");
    }

    private CustomGlSurfaceView mCustomGlSurfaceView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCustomGlSurfaceView = new CustomGlSurfaceView(this);
        setContentView(mCustomGlSurfaceView);
        teset();
    }

    /**
     *  反射构造CaptureRequest.Key
     * @param key
     * @param type
     * @param <T>
     * @return
     */
    public static <T> CaptureRequest.Key<T> newInstanceKey(String key, Class<T> type){
        Class clazz = CaptureRequest.class;
        Class<?>[] innerClass = clazz.getDeclaredClasses();
        for (Class cla : innerClass) {
            if (cla.getSimpleName().equals("Key")) {
                try {
                    Constructor<CaptureRequest.Key> keyConstructor = cla.getConstructor(String.class,Class.class);
                    return keyConstructor.newInstance(key,type);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public void teset(){
    }


    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        Log.e("Rooney","onUserLeaveHint");
    }

    @Override
    public void onUserInteraction() {
        super.onUserInteraction();
        Log.e("Rooney","onUserInteraction");
    }

    public native void commonJNI(String str);
    public native String getStringFromJNI();
}
