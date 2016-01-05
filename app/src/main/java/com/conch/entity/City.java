package com.conch.entity;

import android.support.annotation.NonNull;

/**
 * Created by haodou on 2016/1/5.
 */
public class City implements android.os.Parcelable {
    /**
     * 城市ID
     */
    private int CityId;
    /**
     * 城市名称
     */
    private String CityName;
    /**
     * 省份
     */
    public String Provinc;


    public int getCityId() {
        return CityId;
    }

    public void setCityId(int cityId) {
        CityId = cityId;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull android.os.Parcel dest, int flags) {
        dest.writeInt(CityId);
        dest.writeString(CityName);
        dest.writeString(Provinc);
    }

    public static final android.os.Parcelable.Creator<City> CREATOR =
            new android.os.Parcelable.Creator<City>() {

                @NonNull
                @Override
                public City createFromParcel(@NonNull android.os.Parcel in) {
                    City obj = new City();
                    obj.CityId = in.readInt();
                    obj.CityName = in.readString();
                    obj.Provinc = in.readString();
                    return obj;
                }

                @NonNull
                @Override
                public City[] newArray(int size) {
                    return new City[size];
                }
            };
}
