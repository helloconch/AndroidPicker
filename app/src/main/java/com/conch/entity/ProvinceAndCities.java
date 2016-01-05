package com.conch.entity;

import android.support.annotation.NonNull;

import java.util.ArrayList;

/**
 * Created by haodou on 2016/1/5.
 */
public class ProvinceAndCities implements android.os.Parcelable {
    // 名字与json中的字段保持一致，勿更改
    /**
     * 省份ID'
     */
    private int ProvinceId;
    /**
     * 省份名称
     */
    private String ProvinceName;

    /**
     * 城市列表
     */
    private ArrayList<City> Citys;

    public int getProvinceId() {
        return ProvinceId;
    }

    public void setProvinceId(int provinceId) {
        ProvinceId = provinceId;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public ArrayList<City> getCitys() {
        return Citys;
    }

    public void setCitys(ArrayList<City> citys) {
        Citys = citys;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull android.os.Parcel dest, int flags) {
        dest.writeInt(ProvinceId);
        dest.writeString(ProvinceName);
        dest.writeList(Citys);
    }

    public static final android.os.Parcelable.Creator<ProvinceAndCities> CREATOR =
            new android.os.Parcelable.Creator<ProvinceAndCities>() {

                @NonNull
                @Override
                public ProvinceAndCities createFromParcel(@NonNull android.os.Parcel in) {
                    ProvinceAndCities obj = new ProvinceAndCities();
                    obj.ProvinceId = in.readInt();
                    obj.ProvinceName = in.readString();
                    //noinspection unchecked
                    obj.Citys = in.readArrayList(City.class.getClassLoader());
                    return obj;
                }

                @NonNull
                @Override
                public ProvinceAndCities[] newArray(int size) {
                    return new ProvinceAndCities[size];
                }
            };
}
