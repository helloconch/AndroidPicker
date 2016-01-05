package com.conch.timepicker;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.conch.androidpicker.R;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by haodou on 2015/11/27.
 */
public class NumberDialog extends AppCompatDialog implements View.OnClickListener {
    private View rootView; // 总的布局
    private OnSelectListerner onSelectListerner;
    private View btnSubmit;
    private View btnCancel;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    WheelNumber wheelNumber;

    //小数 个位数 十位数 百位数 千位数 是否含有小数点
    public enum Type {
        UNITS_DIGIT_HAS, TENS_DIGIT_HAS, HUNDREDS_DIGIT_HAS, THOUSANDS_DIGIT_HAS,
        UNITS_DIGIT_NO, TENS_DIGIT_NO, HUNDREDS_DIGIT_NO, THOUSANDS_DIGIT_NO
    }

    public NumberDialog(Context context) {
        super(context);
    }

    public NumberDialog(Context context, int theme) {
        super(context, theme);
    }

    public NumberDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     *
     * @param context
     * @param type UNITS_DIGIT_HAS个位数带小数点
     *             UNITS_DIGIT_NO个位数不带小数点
     *             TENS_DIGIT_HAS十位数带小数点
     *             TENS_DIGIT_NO十位数不带小数点
     *             HUNDREDS_DIGIT_HAS百位数带小数点
     *             HUNDREDS_DIGIT_NO百位数不带小数点
     *             THOUSANDS_DIGIT_HAS千位数带小数点
     *             THOUSANDS_DIGIT_NO千位数不带小数点
     * @param title
     */
    public NumberDialog(Context context, Type type, String title) {
        super(context);
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCanceledOnTouchOutside(true);
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.layout_number_dialog, null);
        // -----确定和取消按钮
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        TextView timeTitle = (TextView) rootView.findViewById(R.id.timeTitle);
        if (!TextUtils.isEmpty(title)) {
            timeTitle.setText(title);
        }
        final View timepickerview = rootView.findViewById(R.id.timepicker);
        ScreenInfo screenInfo = new ScreenInfo((Activity) context);
        wheelNumber = new WheelNumber(timepickerview, type);
        wheelNumber.screenheight = screenInfo.getHeight();
        wheelNumber.setPicker();
//        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(300,LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        rootView.setLayoutParams(params);
        setContentView(rootView);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (onSelectListerner != null) {
                try {
                    onSelectListerner.selectData(wheelNumber.getData());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dismiss();
            return;
        }
    }

    public interface OnSelectListerner {
        void selectData(String data);
    }

    public void setOnSelectListener(OnSelectListerner myOnSelectListener) {
        this.onSelectListerner = myOnSelectListener;
    }


}
