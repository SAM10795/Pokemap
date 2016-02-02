package com.example.sam10795.pokemap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Movie;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.SystemClock;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;

import java.io.InputStream;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

/**
 * Created by SAM10795 on 28-08-2015.
 */
public class PKMNdraw extends View{
    private Context mContext;
    private Activity activity;
    Canvas canvas;
    int al = 255;
    int x = 360, y = 240;
    int xf,yf;
    int count = 0;
    float pres = 0;
    long dt;
    int[] drawa;
    boolean fade =false;
    Resources res;
    Bitmap b0,b1,b2,b3,b4,b5,b6,b7,b8;
    public PKMNdraw(Context context, Activity activityl, int[] drawb, int xt, int yt,boolean fade)
    {
        super(context);
        mContext = context;
        activity = activityl;
        x = xt;
        y = yt;
        xf = x+250;
        yf = y+250;
        drawa = drawb;
        Log.e("This",Integer.toString(drawa[0]));
        this.fade = fade;
        res = getContext().getResources();
        b0 = BitmapFactory.decodeResource(res,drawa[0]);
        b1 = BitmapFactory.decodeResource(res,drawa[1]);
        b2 = BitmapFactory.decodeResource(res,drawa[2]);
        b3 = BitmapFactory.decodeResource(res,drawa[3]);
        b4 = BitmapFactory.decodeResource(res,drawa[4]);
        b5 = BitmapFactory.decodeResource(res,drawa[5]);
        b6 = BitmapFactory.decodeResource(res,drawa[6]);
        b7 = BitmapFactory.decodeResource(res,drawa[7]);
        b8 = BitmapFactory.decodeResource(res,drawa[8]);
    }
    Paint paint = new Paint();
    long st = System.currentTimeMillis();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        long et = System.currentTimeMillis();
        int factor = (int)(et-st)%2500;
        //x=960-factor/3;
        //y =480-factor/5;
        if(al>0&&(et-st>2700)&&fade)
        {
                al = al - 3;
                Log.e("AL",Integer.toString(al));
                paint.setAlpha(al);
        }
        if(al<0)
        {
            paint.setAlpha(0);
        }
            long t = System.currentTimeMillis() % 2550;
            if (t < 1800) {
                t = t % 600;
                if (t < 150) {
                    canvas.drawBitmap(b0, x, y, paint);
                    invalidate();
                } else if (t >= 150 && t < 300) {
                    canvas.drawBitmap(b1, x, y, paint);
                    invalidate();
                } else if (t >= 300 && t < 450) {
                    canvas.drawBitmap(b2, x, y, paint);
                    invalidate();
                } else {
                    canvas.drawBitmap(b3, x, y, paint);
                    invalidate();
                }
            } else {
                t = t - 1800;
                if (t < 150) {
                    canvas.drawBitmap(b4, x, y, paint);
                    invalidate();
                } else if (t >= 150 && t < 300) {
                    canvas.drawBitmap(b5, x, y, paint);
                    invalidate();
                } else if (t >= 300 && t < 450) {
                    canvas.drawBitmap(b6, x, y, paint);
                    invalidate();
                } else if (t >= 450 && t < 600) {
                    canvas.drawBitmap(b7, x, y, paint);
                    invalidate();
                } else {
                    canvas.drawBitmap(b8, x, y, paint);
                    invalidate();
                }


        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        count++;
        /*if(count%2==0)
        {
            xf = (int)event.getX();
            yf = (int)event.getY();
            redraw();
            triggercry();
        }
        else
        {
            x = (int)event.getX();
            y = (int)event.getY();
        }*/
        return super.onTouchEvent(event);
    }

    void redraw()
    {
        int scale = Math.max(Math.abs(xf-x),Math.abs(yf-y));
        if(scale>0) {
            //b0 = Bitmap.createScaledBitmap(k0,scale,scale,false);
            //b1 = Bitmap.createScaledBitmap(k1,scale,scale,false);
        }
    }

    void triggercry()
    {
        MediaPlayer mediaPlayer = MediaPlayer.create(mContext,R.raw.inf);
        if(!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
    }
}
