package com.conch.timepicker;

import android.app.Activity;
import android.util.DisplayMetrics;

/**
 * 得到屏幕宽高密度等
 */
public class ScreenInfo {
    private Activity activity;
    /**
     * 屏幕宽度（像素）
     */
    private int width;
    /**
     * 屏幕高度（像素）
     */
    private int height;
    /**
     * 屏幕密度（0.75 / 1.0 / 1.5）
     */
    private float density;
    /**
     * 屏幕密度DPI（120 / 160 / 240）
     */
    private int densityDpi;

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity context) {
        this.activity = context;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int widthValue) {
        this.width = widthValue;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int heightValue) {
        this.height = heightValue;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float densityValue) {
        this.density = densityValue;
    }

    public int getDensityDpi() {
        return densityDpi;
    }

    public void setDensityDpi(int densityDpiValue) {
        this.densityDpi = densityDpiValue;
    }

    public ScreenInfo(Activity context) {
        this.activity = context;
        ini();
    }

    private void ini() {
        DisplayMetrics metric = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;
        height = metric.heightPixels;
        density = metric.density;
        densityDpi = metric.densityDpi;
    }


}
