package com.conch.timepicker;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.conch.androidpicker.R;
import com.conch.entity.City;
import com.conch.entity.ProvinceAndCities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 城市选择PopupWindow
 */
public class CityPopupWindow extends PopupWindow implements OnClickListener {
    public enum Type {
        PROVINCE, CITY
    }

    private View rootView; // 总的布局
    WheelCity wheelCity;
    private View btnSubmit;
    private View btnCancel;
    private static final String TAG_SUBMIT = "submit";
    private static final String TAG_CANCEL = "cancel";
    private OnSelectListener selectListener;

    public CityPopupWindow(Context context) {
        super(context);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);
        // 这样设置才能点击屏幕外dismiss窗口
        this.setBackgroundDrawable(new BitmapDrawable());
        this.setOutsideTouchable(true);
        this.setAnimationStyle(R.style.timepopwindow_anim_style);
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        rootView = mLayoutInflater.inflate(R.layout.layout_city_popupwindow, null);
        //确定和取消按钮
        btnSubmit = rootView.findViewById(R.id.btnSubmit);
        btnSubmit.setTag(TAG_SUBMIT);
        btnCancel = rootView.findViewById(R.id.btnCancel);
        btnCancel.setTag(TAG_CANCEL);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        //城市滚轮
        final View citypickerview = rootView.findViewById(R.id.timepicker);
        ScreenInfo screenInfo = new ScreenInfo((Activity) context);
        wheelCity = new WheelCity(citypickerview);
        wheelCity.screenheight = screenInfo.getHeight();
        setContentView(rootView);
    }

    public void showAtLocation(View parent, int gravity, int x, int y) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<ProvinceAndCities> datas = new ArrayList<ProvinceAndCities>();
                ProvinceAndCities p1 = new ProvinceAndCities();
                p1.setProvinceName("北京");
                p1.setCitys(new ArrayList<City>());
                datas.add(p1);

                ProvinceAndCities p2 = new ProvinceAndCities();
                p2.setProvinceName("山东");
                ArrayList<City> d2 = new ArrayList<City>();
                City c1 = new City();
                c1.setCityName("济南");
                d2.add(c1);

                City c2 = new City();
                c2.setCityName("青岛");
                d2.add(c2);

                City c3 = new City();
                c3.setCityName("烟台");
                d2.add(c3);

                p2.setCitys(d2);
                datas.add(p2);

                wheelCity.setPicker(datas);
            }
        }, 3000);
        update();
        super.showAtLocation(parent, gravity, x, y);
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wheelCity.setCyclic(cyclic);
    }

    @Override
    public void onClick(View v) {
        String tag = (String) v.getTag();
        if (tag.equals(TAG_CANCEL)) {
            dismiss();
            return;
        } else {
            if (selectListener != null) {
                try {
                    String data = wheelCity.getData();
                    selectListener.onTimeSelect(data);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            dismiss();
            return;
        }
    }

    public interface OnSelectListener {
        void onTimeSelect(String data);
    }

    public void setOnSelectListener(OnSelectListener selectListenerImpl) {
        this.selectListener = selectListenerImpl;
    }

}
