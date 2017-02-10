package com.tarenwang.floatviewfinaldemo;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Administrator on 2017/2/9.
 */

public class FloatViewService extends Service {

    private FloatView mFloatView;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new FloatViewServiceBinder();

    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFloatView = new FloatView(this);
    }

    public void showFloat() {
        if (mFloatView != null) {
            mFloatView.show();
        }
    }

    public void hideFloat() {
        if (mFloatView != null) {
            mFloatView.hide();
        }
    }

    public void destroyFloat() {
        if (mFloatView != null) {
            mFloatView.destroy();
        }
        mFloatView = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        destroyFloat();
    }

    public class FloatViewServiceBinder extends Binder {
        public FloatViewService getService() {
            return FloatViewService.this;
        }
    }
}
