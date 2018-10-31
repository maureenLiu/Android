package com.example.maureen.mytestbindservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.lang.ref.WeakReference;

public class TestRemoteService extends Service {
    private final String TAG= TestRemoteService.class.getSimpleName();
    private IBinder mRemoteBinder = new RemoteServiceImpl(this);

    private static class RemoteServiceImpl extends IRemoteServiceAidlInterface.Stub {
        private final String TAG= RemoteServiceImpl.class.getSimpleName();
        private WeakReference<TestRemoteService> mRemoteService;
        public RemoteServiceImpl(TestRemoteService service) {
            mRemoteService = new WeakReference<>(service);
        }
        @Override
        public void testFunc1() {
            Log.d(TAG,"testFunc1");
            Log.d(TAG,Log.getStackTraceString(new Throwable()));
            mRemoteService.get().testMyFunc1();
        }

        @Override
        public void testFunc2() {
            Log.d(TAG,"testFunc2");
            mRemoteService.get().testMyFunc2();
        }
    }

    private void testMyFunc1() {
        Log.d(TAG,"testMyFunc1");
        Log.d(TAG,Log.getStackTraceString(new Throwable()));
    }

    private void testMyFunc2() {
        Log.d(TAG,"testMyFunc2");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind:mRemoteBinder=" + mRemoteBinder);
        return mRemoteBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }
}
