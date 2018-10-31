package com.example.maureen.mytestbindservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class TestBindServiceActivity extends Activity {
    private static final String TAG = TestBindServiceActivity.class.getSimpleName();
    private static final String ACTION_BIND_LOCAL_SERVICE = "maureen.intent.action.BIND_LOCAL_SERVICE";
    private Button mBindLocalServiceBtn;
    private static ServiceConnection mLocalConnection;
    private static TestLocalService mLocalService = null;

    private static final String ACTION_BIND_REMOTE_SERVICE = "maureen.intent.action.BIND_REMOTE_SERVICE";
    private Button mBindRemoteServiceBtn;
    private static ServiceConnection mRemoteConnection;


    private static class TestLocalConenction implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected:iBinder=" + iBinder);
            mLocalService = ((TestLocalService.TestLocalServiceBinder)iBinder).getService();
            mLocalService.testFunc();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mLocalService = null;
        }
    }

    private static class TestRemoteConection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG,"onServiceConnected:iBinder=" + iBinder);
            IRemoteServiceAidlInterface remoteService = IRemoteServiceAidlInterface.Stub.asInterface(iBinder);
            try {
                remoteService.testFunc1();
                remoteService.testFunc2();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }

    private static class ButtonClickListener implements View.OnClickListener {
        private WeakReference<TestBindServiceActivity> mActivity;
        public ButtonClickListener(TestBindServiceActivity activity) {
            mActivity = new WeakReference<>(activity);
        }
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.bind_local_service:
                    Intent localIntent = new Intent();
                    //Without this, throw exception.
                    localIntent.setPackage("com.example.maureen.mytestbindservice");
                    localIntent.setAction(ACTION_BIND_LOCAL_SERVICE);
                    mActivity.get().bindService(localIntent, mLocalConnection, BIND_AUTO_CREATE);
                    break;
                case R.id.bind_remote_service:
                    Intent remoteIntent = new Intent();
                    //Without this, throw exception.
                    remoteIntent.setPackage("com.example.maureen.mytestbindservice");
                    remoteIntent.setAction(ACTION_BIND_REMOTE_SERVICE);
                    mActivity.get().bindService(remoteIntent, mRemoteConnection, BIND_AUTO_CREATE);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_test_bind_service);
        mLocalConnection = new TestLocalConenction();
        mRemoteConnection = new TestRemoteConection();
        mBindLocalServiceBtn = findViewById(R.id.bind_local_service);
        mBindLocalServiceBtn.setOnClickListener(new ButtonClickListener(this));
        mBindRemoteServiceBtn = findViewById(R.id.bind_remote_service);
        mBindRemoteServiceBtn.setOnClickListener(new ButtonClickListener(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        unbindService(mLocalConnection);
        unbindService(mRemoteConnection);
    }
}
