package com.tarenwang.floatviewfinaldemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private FloatViewService mFloatViewService;

    private Button mShow;
    private Button mHide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        mShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFloatingView();
                Toast.makeText(getApplicationContext(),"show",Toast.LENGTH_SHORT).show();
            }
        });
        mHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hideFloatingView();

            }
        });
        try {
            Intent intent = new Intent(this, FloatViewService.class);
            startService(intent);
            bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
        }

    }

    private void init() {
        mShow = (Button) findViewById(R.id.btn_show_float);
        //mShow.setOnClickListener(this);
        mHide = (Button) findViewById(R.id.btn_hide_float);
        //mHide.setOnClickListener(this);
    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn_show_float:
//                Toast.makeText(getApplicationContext(),"show",Toast.LENGTH_SHORT).show();
//                showFloatingView();
//                try {
//                    Intent intent = new Intent(this, FloatViewService.class);
//                    startService(intent);
//                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//                } catch (Exception e) {
//                }
//                break;
//            case R.id.btn_hide_float:
//                hideFloatingView();
//                try {
//                    Intent intent = new Intent(this, FloatViewService.class);
//                    startService(intent);
//                    bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
//                } catch (Exception e) {
//                }
//                break;
//            default:
//                break;
//        }
//
//    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ( keyCode == KeyEvent.KEYCODE_BACK ) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 显示悬浮图标
     */
    public void showFloatingView() {
        if ( mFloatViewService != null ) {
            mFloatViewService.showFloat();
        }
    }

    /**
     * 隐藏悬浮图标
     */
    public void hideFloatingView() {
        if ( mFloatViewService != null ) {
            mFloatViewService.hideFloat();
        }
    }

    @Override
    protected void onDestroy() {
        destroy();
        super.onDestroy();
    }

    /**
     * 释放PJSDK数据
     */
    public void destroy() {
        try {
            stopService(new Intent(this, FloatViewService.class));
            unbindService(mServiceConnection);
        } catch (Exception e) {
        }
    }

    /**
     * 连接到Service
     */
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mFloatViewService = ((FloatViewService.FloatViewServiceBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mFloatViewService = null;
        }
    };




}
