# AndroidPicker
选择器（时间/数字/城市）

1.PopupWindow形式<br/>
2.Dialog形式<br/>
3.可以直接使用arr文件形式（http://blog.csdn.net/getchance/article/details/47257389 ）<br/>

参考调用方式
public class MainActivity extends Activity {
    TimePopupWindow pwTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        final Button button = new Button(this);
        button.setText("显示时间选择器");
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(400, 200);
        button.setLayoutParams(params);
        linearLayout.addView(button);
        setContentView(linearLayout);

        //时间选择器PopupWindow
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.ALL, true);
        pwTime.setTime(new Date());
        pwTime.setOnTimeSelectListener(new OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_LONG).show();

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwTime.showAtLocation(button, Gravity.BOTTOM, 0, 0, new Date());
            }
        });
        //时间选择器Dialog
        TimeDialog timeDialog = new TimeDialog(this, TimePopupWindow.Type.ALL, true,"");
        timeDialog.setTime(new Date());
        timeDialog.setOnTimeSelectListener(new TimeDialog.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_LONG).show();
            }
        });
        timeDialog.show();

    }
}

