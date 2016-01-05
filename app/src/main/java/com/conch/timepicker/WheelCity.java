package com.conch.timepicker;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.conch.androidpicker.R;
import com.conch.entity.ProvinceAndCities;

import java.util.List;

public class WheelCity {
    private static final int NUMBER_0 = 0;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_3 = 3;
    private static final int NUMBER_9 = 9;
    private static final int NUMBER_12 = 12;
    private static final int NUMBER_31 = 31;
    private static final int NUMBER_30 = 30;
    private static final int NUMBER_4 = 4;
    private static final int NUMBER_100 = 100;
    private static final int NUMBER_400 = 400;
    private static final int NUMBER_29 = 29;
    private static final int NUMBER_28 = 28;
    private static final int NUMBER_23 = 23;
    private static final int NUMBER_59 = 59;
    private View view;
    public int screenheight;
    //加载
    ProgressBar progressBar;
    //省
    WheelView provinceView;
    //市
    WheelView cityView;

    LinearLayout layoutCity;

    public View getView() {
        return view;
    }

    public void setView(View container) {
        this.view = container;
    }

    public WheelCity(View container) {
        super();
        this.view = container;
        setView(container);
    }

    public void setPicker(final List<ProvinceAndCities> datas) {
        Context context = view.getContext();
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        layoutCity = (LinearLayout) view.findViewById(R.id.layoutCity);
        layoutCity.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.INVISIBLE);
        provinceView = (WheelView) view.findViewById(R.id.provinceView);
        //设置省的数据
        provinceView.setAdapter(new CityWheelAdapter(CityPopupWindow.Type.PROVINCE, datas, 0));
        // 添加文字
//      thousandsDigitView.setLabel();
        // 初始化时数据
        provinceView.setCurrentItem(NUMBER_1);

        // 添加监听
        OnWheelChangedListener provinceChaneListener = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                cityView = (WheelView) view.findViewById(R.id.cityView);
                cityView.setAdapter(new CityWheelAdapter(CityPopupWindow.Type.CITY, datas, newValue));
                cityView.setCurrentItem(NUMBER_1);
            }
        };
        provinceView.addChangingListener(provinceChaneListener);
        initWheelViewState();
    }

    private void initWheelViewState() {
        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = (screenheight / NUMBER_100) * NUMBER_3;
        provinceView.TEXT_SIZE = textSize;
        cityView.TEXT_SIZE = textSize;
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        provinceView.setCyclic(cyclic);
        cityView.setCyclic(cyclic);
    }

    public String getData() {
        StringBuffer sb = new StringBuffer();

        sb.append((provinceView.getCurrentItem()))
                .append((cityView.getCurrentItem()));


        return sb.toString();
    }
}
