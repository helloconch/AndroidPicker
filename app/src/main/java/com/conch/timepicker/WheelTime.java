package com.conch.timepicker;

import android.content.Context;
import android.view.View;

import com.conch.androidpicker.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class WheelTime {
    public static DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final int NUMBER_0 = 0;
    private static final int NUMBER_2 = 2;
    private static final int NUMBER_1 = 1;
    private static final int NUMBER_3 = 3;
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
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private WheelView wvHours;
    private WheelView wvMins;
    public int screenheight;

    private TimePopupWindow.Type type;
    private static int START_YEAR;
    private static int END_YEAR;

    public View getView() {
        return view;
    }

    public void setView(View container) {
        this.view = container;
    }

    public static int getStartYear() {
        return START_YEAR;
    }

    public static void setStartYear(int startYearValue) {
        START_YEAR = startYearValue;
    }

    public static int getEndYear() {
        return END_YEAR;
    }

    public static void setEndYear(int endYearValue) {
        END_YEAR = endYearValue;
    }

    public WheelTime(View container) {
        super();
        this.view = container;
        type = TimePopupWindow.Type.ALL;
        setView(container);
    }

    public WheelTime(View container, TimePopupWindow.Type typeValue) {
        super();
        this.view = container;
        this.type = typeValue;
        setView(container);
    }

    public void setPicker(int year, int month, int day) {
        this.setPicker(new DateBean(year, month, day, 0, 0));
    }

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void setPicker(DateBean dateBean) {
        int year = dateBean.getYear();
        int month = dateBean.getMonth();
        int day = dateBean.getDay();
        int h = dateBean.getHour();
        int m = dateBean.getMinute();
        // 添加大小月月份并将其转换为list,方便之后的判断
        String[] monthsBig = {"1", "3", "5", "7", "8", "10", "12"};
        String[] monthsLittle = {"4", "6", "9", "11"};

        final List<String> listBig = Arrays.asList(monthsBig);
        final List<String> listLittle = Arrays.asList(monthsLittle);

        Context context = view.getContext();
        //年
        wvYear = (WheelView) view.findViewById(R.id.year);
        // 设置"年"的显示数据
        wvYear.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));
        // 添加文字
        wvYear.setLabel(context.getString(R.string.timepicker_year));
        // 初始化时显示的数据
        wvYear.setCurrentItem(year - START_YEAR);

        // 月
        wvMonth = (WheelView) view.findViewById(R.id.month);
        wvMonth.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_12));
        wvMonth.setLabel(context.getString(R.string.timepicker_month));
        wvMonth.setCurrentItem(month);

        // 日
        wvDay = (WheelView) view.findViewById(R.id.day);
        // 判断大小月及是否闰年,用来确定"日"的数据
        if (listBig.contains(String.valueOf(month + NUMBER_1))) {
            wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_31));
        } else if (listLittle.contains(String.valueOf(month + NUMBER_1))) {
            wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_30));
        } else {
            // 闰年
            if ((year % NUMBER_4 == NUMBER_0
                    && year % NUMBER_100 != NUMBER_0) || year % NUMBER_400 == NUMBER_0) {
                wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_29));
            } else {
                wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_28));
            }
        }
        wvDay.setLabel(context.getString(R.string.timepicker_day));
        wvDay.setCurrentItem(day - NUMBER_1);


        wvHours = (WheelView) view.findViewById(R.id.hour);
        wvHours.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_23));
        // 添加文字
        wvHours.setLabel(context.getString(R.string.pickerview_hours));
        wvHours.setCurrentItem(h);

        wvMins = (WheelView) view.findViewById(R.id.min);
        wvMins.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_59));
        // 添加文字
        wvMins.setLabel(context.getString(R.string.timepicker_minutes));
        wvMins.setCurrentItem(m);

        // 添加"年"监听
        OnWheelChangedListener wheelListenerYear = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int yearNum = newValue + START_YEAR;
                // 判断大小月及是否闰年,用来确定"日"的数据
                int maxItem = NUMBER_30;
                if (listBig
                        .contains(String.valueOf(wvMonth.getCurrentItem() + NUMBER_1))) {
                    wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_31));
                    maxItem = NUMBER_31;
                } else if (listLittle.contains(String.valueOf(wvMonth
                        .getCurrentItem() + NUMBER_1))) {
                    wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_30));
                    maxItem = NUMBER_30;
                } else {
                    if ((yearNum % NUMBER_4 == NUMBER_0 && yearNum % NUMBER_100 != NUMBER_0)
                            || yearNum % NUMBER_400 == NUMBER_0) {
                        wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_29));
                        maxItem = NUMBER_29;
                    } else {
                        wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_28));
                        maxItem = NUMBER_28;
                    }
                }
                if (wvDay.getCurrentItem() > maxItem - 1) {
                    wvDay.setCurrentItem(maxItem - 1);
                }
            }
        };
        // 添加"月"监听
        OnWheelChangedListener wheelListenerMonth = new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                int monthNum = newValue + NUMBER_1;
                int maxItem = NUMBER_30;
                // 判断大小月及是否闰年,用来确定"日"的数据
                if (listBig.contains(String.valueOf(monthNum))) {
                    wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_31));
                    maxItem = NUMBER_31;
                } else if (listLittle.contains(String.valueOf(monthNum))) {
                    wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_30));
                    maxItem = NUMBER_30;
                } else {
                    if (((wvYear.getCurrentItem() + START_YEAR) % NUMBER_4 == NUMBER_0 && (wvYear
                            .getCurrentItem() + START_YEAR) % NUMBER_100 != NUMBER_0)
                            || (wvYear.getCurrentItem() + START_YEAR) % NUMBER_400 == NUMBER_0) {
                        wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_29));
                        maxItem = NUMBER_29;
                    } else {
                        wvDay.setAdapter(new NumericWheelAdapter(NUMBER_1, NUMBER_28));
                        maxItem = NUMBER_28;
                    }
                }
                if (wvDay.getCurrentItem() > maxItem - NUMBER_1) {
                    wvDay.setCurrentItem(maxItem - NUMBER_1);
                }

            }
        };
        wvYear.addChangingListener(wheelListenerYear);
        wvMonth.addChangingListener(wheelListenerMonth);
        initWheelViewState();


    }

    private void initWheelViewState() {
        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = NUMBER_0;
        switch (type) {
            case ALL:
                textSize = (screenheight / NUMBER_100) * NUMBER_2;
                break;
            case YEAR_MONTH_DAY:
                textSize = (screenheight / NUMBER_100) * NUMBER_2;
                wvHours.setVisibility(View.GONE);
                wvMins.setVisibility(View.GONE);
                break;
            case HOURS_MINS:
                textSize = (screenheight / NUMBER_100) * NUMBER_2;
                wvYear.setVisibility(View.GONE);
                wvMonth.setVisibility(View.GONE);
                wvDay.setVisibility(View.GONE);
                break;
            case MONTH_DAY_HOUR_MIN:
                textSize = (screenheight / NUMBER_100) * NUMBER_2;
                wvYear.setVisibility(View.GONE);
                break;
            case YEAR_MONTH:
                textSize = (screenheight / NUMBER_100) * NUMBER_2;
                wvDay.setVisibility(View.GONE);
                wvHours.setVisibility(View.GONE);
                wvMins.setVisibility(View.GONE);
            default:
                break;
        }

        wvDay.TEXT_SIZE = textSize;
        wvMonth.TEXT_SIZE = textSize;
        wvYear.TEXT_SIZE = textSize;
        wvHours.TEXT_SIZE = textSize;
        wvMins.TEXT_SIZE = textSize;
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        wvYear.setCyclic(cyclic);
        wvMonth.setCyclic(cyclic);
        wvDay.setCyclic(cyclic);
        wvHours.setCyclic(cyclic);
        wvMins.setCyclic(cyclic);
    }

    public String getTime() {
        StringBuffer sb = new StringBuffer();
        sb.append((wvYear.getCurrentItem() + START_YEAR)).append("-")
                .append((wvMonth.getCurrentItem() + NUMBER_1)).append("-")
                .append((wvDay.getCurrentItem() + NUMBER_1)).append(" ")
                .append(wvHours.getCurrentItem()).append(":")
                .append(wvMins.getCurrentItem());
        return sb.toString();
    }
}
