package com.conch.timepicker;


import com.conch.entity.ProvinceAndCities;

import java.util.List;

/**
 * City Wheel adapter.
 */
public class CityWheelAdapter implements WheelAdapter {

    /**
     * The default min value
     */
    public static final int DEFAULT_MAX_VALUE = 9;

    /**
     * The default max value
     */
    private static final int DEFAULT_MIN_VALUE = 0;

    // Values
    private int minValue;
    private int maxValue;

    //Type

    private CityPopupWindow.Type type;

    List<ProvinceAndCities> datas;
    //父节点下标
    private int pos;

    public CityWheelAdapter(CityPopupWindow.Type type, List<ProvinceAndCities> datas, int pos) {
        this.type = type;
        this.datas = datas;
        this.pos = pos;
        minValue = DEFAULT_MIN_VALUE;
        maxValue = DEFAULT_MAX_VALUE;
        switch (type) {
            case PROVINCE:
                maxValue = datas.size();
                break;
            case CITY:
                maxValue = datas.get(pos).getCitys().size();
                break;
        }
    }


    @Override
    public String getItem(int index) {
        if (index >= 0 && index < getItemsCount()) {
            switch (type) {
                case PROVINCE:
                    return this.datas.get(index).getProvinceName();
                case CITY:
                    return this.datas.get(pos).getCitys().get(index).getCityName();
            }
        }
        return null;
    }

    @Override
    public int getItemsCount() {
        return maxValue;
    }

    @Override
    public int getMaximumLength() {
        int max = Math.max(Math.abs(maxValue), Math.abs(minValue));
        int maxLen = Integer.toString(max).length();
        if (minValue < 0) {
            maxLen++;
        }
        return maxLen;
    }
}
