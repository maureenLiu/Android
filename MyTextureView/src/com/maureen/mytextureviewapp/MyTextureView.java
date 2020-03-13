package com.maureen.mytextureviewapp;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.util.Log;
import android.view.TextureView;

public class MyTextureView extends TextureView {
    private final String TAG = "MyTextureView";

    public MyTextureView(Context context) {
        super(context);
        Log.i(TAG, "MyTextureView:1 param");
        init();
    }

    public MyTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "MyTextureView:2 param");
        init();
    }

    public MyTextureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "MyTextureView:3 param");
        init();
    }

    private void init() {
        Log.i(TAG, "init");
        setSurfaceTextureListener(mSurfaceTextureListener);
    }

    private TextureView.SurfaceTextureListener mSurfaceTextureListener = new TextureView.SurfaceTextureListener() {
        @Override
        public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "onSurfaceTextureAvailable");
        }

        @Override
        public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            Log.i(TAG, "onSurfaceTextureSizeChanged");
        }

        @Override
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
            Log.i(TAG, "onSurfaceTextureDestroyed");
            return false;
        }

        @Override
        public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            Log.i(TAG, "onSurfaceTextureUpdated");
        }
    };
}
