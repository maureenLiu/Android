package com.maureen.mytextureviewapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

public class MyTextureService extends Service {
    private final String TAG = "MyTextureService";
    private View mPopView;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mLayoutParams;
    private MyTextureView mMyTextureView;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG,"onCreate");
        mWindowManager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mPopView = inflater.inflate(R.layout.preview, null);
        mMyTextureView = (MyTextureView)mPopView.findViewById(R.id.texture);
        mMyTextureView.setVisibility(View.INVISIBLE);

        showWindow();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind");
        return null;
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand, intent: " + intent + " flags: " + flags + " startId: " + startId);
        return super.onStartCommand(intent, flags, startId);
    }

    private void showWindow() {
        Log.d(TAG,"showWindow");
        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_DRAG;
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED;
        mLayoutParams.systemUiVisibility = View.STATUS_BAR_DISABLE_HOME | View.STATUS_BAR_DISABLE_BACK | View.STATUS_BAR_DISABLE_RECENT |
                                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN |
                                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
        if (!mPopView.isAttachedToWindow()) {
            mWindowManager.addView(mPopView, mLayoutParams);
            //mWindowManager.updateViewLayout(mPopView, mLayoutParams);
            mPopView.setVisibility(View.VISIBLE);
            mMyTextureView.setVisibility(View.VISIBLE);
        }
        Intent activityIntent = new Intent(getApplicationContext(),MyTextureViewActivity.class);
        activityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(activityIntent);
    }
}
