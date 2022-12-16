package com.elbourn.android.clock2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.wear.ambient.AmbientModeSupport;
import processing.android.CompatUtils;
import processing.android.PFragment;

public class MainActivity extends AppCompatActivity implements AmbientModeSupport.AmbientCallbackProvider {

    String TAG = getClass().getSimpleName();

    Sketch sketch = null;
    FrameLayout frameLayout = null;
    PFragment pFragment = null;
    int width = ViewGroup.LayoutParams.MATCH_PARENT;
    int height = ViewGroup.LayoutParams.MATCH_PARENT;
    AmbientModeSupport.AmbientController ambientController = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ambientController = AmbientModeSupport.attach(this);
        frameLayout = new FrameLayout(this);
        frameLayout.setId(CompatUtils.getUniqueViewId());
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(width, height);
        setContentView(frameLayout, layoutParams);
        sketch = new Sketch();
        pFragment = new PFragment(sketch);
        pFragment.setView(frameLayout, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sketch.loop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sketch.noLoop();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (sketch != null) {
            sketch.onRequestPermissionsResult(
                    requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (sketch != null) {
            sketch.onNewIntent(intent);
        }
    }

    @Override
    public AmbientModeSupport.AmbientCallback getAmbientCallback() {
        return new MyAmbientCallback();
    }

    class MyAmbientCallback extends AmbientModeSupport.AmbientCallback {
        @Override
        public void onEnterAmbient(Bundle ambientDetails) {
            // Handle entering ambient mode
            sketch.frameRate(1);
            sketch.setShowSeconds(true);
            sketch.setFontColour(Color.WHITE);
        }

        @Override
        public void onExitAmbient() {
            // Handle exiting ambient mode
            sketch.frameRate(1f/60f);
            sketch.setShowSeconds(false);
            sketch.setFontColour(Color.GRAY);
        }

        @Override
        public void onUpdateAmbient() {
            // Update the content
        }
    }

}