package com.conch.timepicker;

/**
 * Created by haodou on 2015/11/19.
 */
public class DateBean {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public DateBean(int yearParams, int monthParams, int dayParams, int hourParams, int minuteParams) {
        this.year = yearParams;
        this.month = monthParams;
        this.day = dayParams;
        this.hour = hourParams;
        this.minute = minuteParams;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int yearParams) {
        this.year = yearParams;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int monthParams) {
        this.month = monthParams;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int dayParams) {
        this.day = dayParams;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hourParams) {
        this.hour = hourParams;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minuteParams) {
        this.minute = minuteParams;
    }
}
