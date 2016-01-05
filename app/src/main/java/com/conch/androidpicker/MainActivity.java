package com.conch.androidpicker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.conch.timepicker.CityPopupWindow;
import com.conch.timepicker.TimeDialog;
import com.conch.timepicker.TimePopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TimePopupWindow pwTime;
    CityPopupWindow cityPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Popupwindow形式展示时间
//                pwTime.showAtLocation(view, Gravity.BOTTOM, 0, 0, new Date());

                //popupwindow形式展示城市

                cityPopupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);

            }
        });

        //时间选择器PopupWindow
        pwTime = new TimePopupWindow(this, TimePopupWindow.Type.ALL, true);
        pwTime.setTime(new Date());
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_LONG).show();

            }
        });


        //时间选择器Dialog
        TimeDialog timeDialog = new TimeDialog(this, TimePopupWindow.Type.ALL, true, "");
        timeDialog.setTime(new Date());
        timeDialog.setOnTimeSelectListener(new TimeDialog.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Toast.makeText(MainActivity.this, format.format(date), Toast.LENGTH_LONG).show();
            }
        });
        timeDialog.show();


        //城市选择器PopupWindow
        cityPopupWindow = new CityPopupWindow(this);
        cityPopupWindow.setOnSelectListener(new CityPopupWindow.OnSelectListener() {
            @Override
            public void onTimeSelect(String data) {
                Toast.makeText(MainActivity.this, data, Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
