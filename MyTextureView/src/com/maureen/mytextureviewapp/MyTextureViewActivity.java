package com.maureen.mytextureviewapp;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class MyTextureViewActivity extends Activity {
    private final String TAG = "MyTextureViewActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate:"+ this);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        View myView = new View(this);
        setContentView(myView);
    }

    private void setFullScreen() {
        Log.i(TAG, "setFullScreen");
    }

}
