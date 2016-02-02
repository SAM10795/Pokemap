package com.example.sam10795.pokemap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.util.List;



public class CamView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceHolder sfh;
    Camera cam;
    Activity activity;

    int x = 1, y =1;

    public CamView(Context context, Activity activity1)
    {
        super(context);
        activity = activity1;
        sfh = getHolder();
        sfh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        sfh.addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        cam = Camera.open();
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(Camera.CameraInfo.CAMERA_FACING_BACK, cameraInfo);
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        int degrees = 0;
        switch (rotation) {
            case Surface.ROTATION_0: degrees = 0; break;
            case Surface.ROTATION_90: degrees = 90; break;
            case Surface.ROTATION_180: degrees = 180; break;
            case Surface.ROTATION_270: degrees = 270; break;
        }
        cam.setDisplayOrientation((cameraInfo.orientation - degrees + 360) % 360);

        try{
            cam.setPreviewDisplay(sfh);
        }
        catch(IOException io)
        {
            io.printStackTrace();
            Log.e("SF","Error");
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Camera.Parameters cp = cam.getParameters();
        int w=0,h=0;
        cp.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        List<Camera.Size> prevSizes = cp.getSupportedPreviewSizes();
        for (Camera.Size s : prevSizes)
        {
            if((s.height >= h) && (s.width >= w))
            {
                h = s.height;
                w = s.width;
            }
        }
        cp.setPreviewSize(w, h);
        cam.setParameters(cp);
        cam.startPreview();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        cam.stopPreview();
        cam.release();
    }
}
