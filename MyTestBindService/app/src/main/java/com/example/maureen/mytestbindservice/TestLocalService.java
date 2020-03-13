package com.example.maureen.mytestbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class TestLocalService extends Service {
    private final String TAG = TestLocalService.class.getSimpleName();
    private IBinder mServiceBinder = new TestLocalServiceBinder();

    public class TestLocalServiceBinder extends Binder {
            public TestLocalService getService() {
                return TestLocalService.this;
            }
    }

    public void testFunc() {
        Log.d(TAG,"testFunc");
        Log.d(TAG, Log.getStackTraceString(new Throwable()));
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind:mServiceBinder=" + mServiceBinder);
        return mServiceBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
