package com.example.sam10795.pokemap;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by SAM10795 on 04-09-2015.
 */
public class Throdraw extends View{

    private Context mContext;
    private Activity mActivity;
    double fx,fy;
    double sx,sy;
    double hw;
    double t;
    int a = 255;

    public Throdraw(Context context,Activity activity,double x,double y, double vx, double vy,double a,double t)
    {
        super(context);
        mContext = context;
        mActivity = activity;
        fx = x;
        fy = y;
        sx = vx;
        sy = vy;
        hw = a;
        this.t = t;
    }
    Resources res = getContext().getResources();
    Bitmap p1 = BitmapFactory.decodeResource(res,R.drawable.pk1);
    Bitmap p2 = BitmapFactory.decodeResource(res,R.drawable.pk2);
    Bitmap p3 = BitmapFactory.decodeResource(res,R.drawable.pk3);
    Bitmap p4 = BitmapFactory.decodeResource(res,R.drawable.pk4);
    Bitmap p5 = BitmapFactory.decodeResource(res,R.drawable.pk5);
    Bitmap p6 = BitmapFactory.decodeResource(res,R.drawable.pk6);
    Bitmap p7 = BitmapFactory.decodeResource(res,R.drawable.pk7);
    Bitmap p8 = BitmapFactory.decodeResource(res,R.drawable.pk8);
    Bitmap p9 = BitmapFactory.decodeResource(res,R.drawable.pk10);
    Bitmap p10 = BitmapFactory.decodeResource(res,R.drawable.pk11);
    Bitmap p11 = BitmapFactory.decodeResource(res,R.drawable.pk12);
    Bitmap p12 = BitmapFactory.decodeResource(res,R.drawable.pk13);
    Bitmap p13 = BitmapFactory.decodeResource(res,R.drawable.pk14);
    Bitmap p14 = BitmapFactory.decodeResource(res,R.drawable.pk15);
    Bitmap p15 = BitmapFactory.decodeResource(res,R.drawable.pk16);
    Bitmap p16 = BitmapFactory.decodeResource(res,R.drawable.pk17);

    Bitmap ps[] = {p1,p2,p3,p4,p5,p6,p7,p8,p9,p10};

    long st = System.currentTimeMillis();
    Paint p = new Paint();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(System.currentTimeMillis()-st<2700)
        {
            long et = System.currentTimeMillis()-st;
            for(int i=0;i<27;i++)
            {
                if((et%(100*(i+1)))==et)
                {
                    canvas.drawBitmap(ps[i%9],getx(i),gety(i),null);
                    Log.e("Drawing at ",Float.toString(getx(i))+Float.toString(gety(i)));
                    break;
                }
            }
            invalidate();
        }
        else if(System.currentTimeMillis()-st<5050){
            canvas.drawBitmap(ps[9],getx(28),gety(28), null);
            invalidate();
        }
        else
        {
            if(a<0) {
                p.setAlpha(0);
            }
            else
            {
                p.setAlpha(a);
            }
            canvas.drawBitmap(p11,getx(28),gety(28)+10,p);
            a--;
        }
    }

    float getx(int j)
    {
        double t0 = (t/20)*j;
        return (float)(fx-(sx*Math.cos(hw)*t0));
    }
    float gety(int j)
    {
        double t0 = (t/20)*j;
        //Log.i("Vy and t are:",Double.toString(sy*Math.abs(Math.sin(hw)))+","+Double.toString(0.5*Math.sin(hw)*9.8));*Math.abs(Math.sin(hw))
        return (float)(fy-((sy*t0))+(0.5*9.8*Math.pow(((t0)),2)));

    }
}
