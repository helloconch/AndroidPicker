package com.conch.timepicker;

import android.content.Context;

public class DensityUtil {

    private DensityUtil() {
    }

    private static final float TEMP_VALUE = 0.5f;

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        float scaleSize = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scaleSize + TEMP_VALUE);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        float scaleSize = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scaleSize + TEMP_VALUE);
    }
}
