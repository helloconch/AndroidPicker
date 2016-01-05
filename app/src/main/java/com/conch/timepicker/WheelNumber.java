package com.conch.timepicker;

import android.content.Context;
import android.view.View;

import com.conch.androidpicker.R;
import com.conch.timepicker.NumberDialog.Type;

public class WheelNumber {
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
    //千位
    WheelView thousandsDigitView;
    // 百位
    WheelView hundredsDigitView;
    //十位
    WheelView tensDigitView;
    //个位数
    WheelView unitsDigitView;
    //小数位
    WheelView decimalDigitView;
    private Type type;
    private static int START_NUM;
    private static int END_NUM;

    public View getView() {
        return view;
    }

    public void setView(View container) {
        this.view = container;
    }

    public static int getStartNum() {
        return START_NUM;
    }

    public static void setStartNum(int startNumValue) {
        START_NUM = startNumValue;
    }

    public static int getEndNum() {
        return END_NUM;
    }

    public static void setEndNum(int endNumValue) {
        END_NUM = endNumValue;
    }


    public WheelNumber(View container, Type typeValue) {
        super();
        this.view = container;
        this.type = typeValue;
        setView(container);
    }

    public void setPicker(int firstNum, int secondNum) {
        this.setPicker();
    }

    /**
     * @Description: TODO 弹出日期时间选择器
     */
    public void setPicker() {

        Context context = view.getContext();

        thousandsDigitView = (WheelView) view.findViewById(R.id.thousandsDigitView);
        // 设置千位的显示数据
        thousandsDigitView.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_9));
        // 添加文字
//      thousandsDigitView.setLabel();
        // 初始化时显示的数据
        thousandsDigitView.setCurrentItem(NUMBER_1);

        hundredsDigitView = (WheelView) view.findViewById(R.id.hundredsDigitView);
        hundredsDigitView.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_9));
        hundredsDigitView.setCurrentItem(NUMBER_1);

        tensDigitView = (WheelView) view.findViewById(R.id.tensDigitView);
        tensDigitView.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_9));
        tensDigitView.setCurrentItem(NUMBER_1);

        unitsDigitView = (WheelView) view.findViewById(R.id.unitsDigitView);
        unitsDigitView.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_9));
        unitsDigitView.setCurrentItem(NUMBER_1);

        decimalDigitView = (WheelView) view.findViewById(R.id.decimalssDigitView);
        decimalDigitView.setAdapter(new NumericWheelAdapter(NUMBER_0, NUMBER_9));
        decimalDigitView.setCurrentItem(NUMBER_1);


        // 添加监听
//        OnWheelChangedListener wheelListenerYear = new OnWheelChangedListener() {
//            @Override
//            public void onChanged(WheelView wheel, int oldValue, int newValue) {
//
//            }
//        };
//        thousandsDigitView.addChangingListener(wheelListenerYear);
        initWheelViewState();

    }

    private void initWheelViewState() {
        // 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
        int textSize = NUMBER_0;
        switch (type) {
            case THOUSANDS_DIGIT_HAS:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                unitsDigitView.setLabel(".");
                break;
            case THOUSANDS_DIGIT_NO:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                decimalDigitView.setVisibility(View.GONE);
                break;

            case HUNDREDS_DIGIT_HAS:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                unitsDigitView.setLabel(".");
                break;
            case HUNDREDS_DIGIT_NO:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                decimalDigitView.setVisibility(View.GONE);
                break;

            case TENS_DIGIT_HAS:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                hundredsDigitView.setVisibility(View.GONE);
                unitsDigitView.setLabel(".");
                break;
            case TENS_DIGIT_NO:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                hundredsDigitView.setVisibility(View.GONE);
                decimalDigitView.setVisibility(View.GONE);
                break;

            case UNITS_DIGIT_HAS:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                hundredsDigitView.setVisibility(View.GONE);
                tensDigitView.setVisibility(View.GONE);
                unitsDigitView.setLabel(".");
                break;
            case UNITS_DIGIT_NO:
                textSize = (screenheight / NUMBER_100) * NUMBER_3;
                thousandsDigitView.setVisibility(View.GONE);
                hundredsDigitView.setVisibility(View.GONE);
                tensDigitView.setVisibility(View.GONE);
                decimalDigitView.setVisibility(View.GONE);
                break;
            default:
                break;
        }

        thousandsDigitView.TEXT_SIZE = textSize;
        hundredsDigitView.TEXT_SIZE = textSize;
        tensDigitView.TEXT_SIZE = textSize;
        unitsDigitView.TEXT_SIZE = textSize;
        decimalDigitView.TEXT_SIZE = textSize;
    }

    /**
     * 设置是否循环滚动
     *
     * @param cyclic
     */
    public void setCyclic(boolean cyclic) {
        thousandsDigitView.setCyclic(cyclic);
        hundredsDigitView.setCyclic(cyclic);
        tensDigitView.setCyclic(cyclic);
        unitsDigitView.setCyclic(cyclic);
        decimalDigitView.setCyclic(cyclic);
    }

    public String getData() {
        StringBuffer sb = new StringBuffer();
        switch (type) {
            case THOUSANDS_DIGIT_HAS:
                sb.append((thousandsDigitView.getCurrentItem()))
                        .append((hundredsDigitView.getCurrentItem()))
                        .append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem()).append(".")
                        .append(decimalDigitView.getCurrentItem());
                break;
            case THOUSANDS_DIGIT_NO:
                sb.append((thousandsDigitView.getCurrentItem()))
                        .append((hundredsDigitView.getCurrentItem()))
                        .append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem());

                break;

            case HUNDREDS_DIGIT_HAS:
                sb.append((hundredsDigitView.getCurrentItem()))
                        .append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem()).append(".")
                        .append(decimalDigitView.getCurrentItem());
                break;
            case HUNDREDS_DIGIT_NO:
                sb.append((hundredsDigitView.getCurrentItem()))
                        .append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem());

                break;

            case TENS_DIGIT_HAS:

                sb.append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem()).append(".")
                        .append(decimalDigitView.getCurrentItem());
                break;
            case TENS_DIGIT_NO:
                sb.append((tensDigitView.getCurrentItem()))
                        .append(unitsDigitView.getCurrentItem());

                break;

            case UNITS_DIGIT_HAS:
                sb.append(unitsDigitView.getCurrentItem()).append(".")
                        .append(decimalDigitView.getCurrentItem());
                break;
            case UNITS_DIGIT_NO:
                sb.append(unitsDigitView.getCurrentItem());
                break;
            default:
                break;
        }

        return sb.toString();
    }
}
