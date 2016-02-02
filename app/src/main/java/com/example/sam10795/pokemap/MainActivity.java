package com.example.sam10795.pokemap;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.v4.view.GestureDetectorCompat;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Created by SAM10795 on 27-08-2015.
 */
public class MainActivity extends Activity implements SensorEventListener,LocationListener,GestureDetector.OnGestureListener{


    FrameLayout frameLayout;
    PKMN p;
    TextView tx,ty,tz,gps,mot;
    int x = 500, y =250;
    boolean caught = false;
    private SensorManager sensorManager;
    private Sensor sensor;
    Location location;
    int[] drab;
    private GestureDetectorCompat gestureDetectorCompat;
    int count = 0;
    boolean light = true;
    float max;
    boolean found = false;
    boolean retry = true;
    ImageView pkb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = (FrameLayout)findViewById(R.id.fl);
        CamView cv = new CamView(this,this);
        frameLayout.addView(cv, 0);
        tx = (TextView)findViewById(R.id.X);
        ty = (TextView)findViewById(R.id.Y);
        tz = (TextView)findViewById(R.id.Z);
        gps = (TextView)findViewById(R.id.GPS);
        pkb = (ImageView)findViewById(R.id.pkb);
        pkb.setVisibility(View.GONE);

        gestureDetectorCompat = new GestureDetectorCompat(this,this);
        mot = (TextView)findViewById(R.id.MOT);
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);
        boolean gpsp = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean ntsp = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if(gpsp)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,this);
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }
        else if(ntsp)
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,5000,5,this);
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        else
        {
            Toast.makeText(this,"Cant get location",Toast.LENGTH_SHORT).show();
        }
        if(location!=null) {
            gps.setText("LAT:" + location.getLatitude() + "LON:" + location.getLongitude());
        }
        else
        {
            gps.setText("Finding Location");
        }
        if((sensor=sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT))!=null)
        {
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
            max = sensor.getMaximumRange();
            //drab = new int[]{R.drawable.z1,R.drawable.z2,R.drawable.z1,R.drawable.z2,R.drawable.z1,R.drawable.z2,R.drawable.z1,R.drawable.z2,R.drawable.z1};
            //frameLayout.addView((new PKMNdraw(this,this,drab,440,100,false)),1);
        }
        else
        {
            ty.setText("SENSOR NOT FOUND");
            frameLayout.addView(new PKMNdraw(this,this,drab,x,y,false),1);
        }

        //imageView.setBackgroundResource(R.drawable.anima);
        //AnimationDrawable fa = (AnimationDrawable)imageView.getBackground();
        //fa.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(!found) {
            p = new PKMN();
            p.setName("PKMN1");
            p.setId(0);
            p.setID("0");
            p.setLv(2);
            p.setShiny(false);
            frameLayout.removeViewAt(1);
            Log.i("Sensor", Float.toString(event.values[0]));
            if (event.values[0] < 50) {
                drab = new int[]{R.drawable.z1, R.drawable.z2, R.drawable.z1, R.drawable.z2, R.drawable.z1, R.drawable.z2, R.drawable.z1, R.drawable.z2, R.drawable.z1};
            } else if (event.values[0] < 1000&&event.values[0]>199) {
                drab = new int[]{R.drawable.i01, R.drawable.i02, R.drawable.i01, R.drawable.i02, R.drawable.i01, R.drawable.i02, R.drawable.i01, R.drawable.i02, R.drawable.i01};
            } else {
                drab = new int[]{R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7, R.drawable.b8, R.drawable.b9};
            }
            p.setBmp(drab);
            found = true;
            frameLayout.addView(new PKMNdraw(this, this, drab, x, y, caught), 1);
            sensorManager.unregisterListener(this);
        }

        //frameLayout.removeViewAt(1);
        //x-=10;
        //y-=10;
        //frameLayout.addView(new anidraw(this,this,x,y));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
        gps.setText("LAT:"+location.getLatitude()+";"+"LON:"+location.getLongitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetectorCompat.onTouchEvent(event);
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            if(!caught)
            {
                count = 0;
                //frameLayout.removeViewAt(2);
            }
        }
        return super.onTouchEvent(event);
    }

    boolean inlimit (float low, float high, float lim,float val)
    {
        return(val>(lim-low)&&val<(high+lim));
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        if(pkb.getVisibility()==View.GONE)
        {
            pkb.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        //Toast.makeText(this,"Fling",Toast.LENGTH_SHORT).show();
        //mot.setText("Point 1 is: "+Float.toString(e1.getX())+" , "+Float.toString(e1.getY())+"\nPoint 2 is: "+Float.toString(e2.getX())+" , "+Float.toString(e2.getY())+
                //"\nVelocity of x is "+velocityX+" and \nVelocity of y is "+velocityY);
        if(found&&retry&&(pkb.getVisibility()==View.VISIBLE)) {
            retry = false;
            frameLayout.addView(new RView(this,x+50,y+50,50,50),3);
            frameLayout.removeViewAt(2);
            //e1 x1 and y1 must lie near pokeball
            float x2 = e2.getX();
            float y2 = e2.getY();
            float x1 = pkb.getX();
            float y1 = pkb.getY();
            pkb.setVisibility(View.GONE);
            double hw = geta(x1, x2, y1, y2);
            double t = gett0(velocityY / 60, hw);
            if (inball((float) (x1 - (((velocityX / 40) * Math.cos(hw) * 27 * t) / 20)),
                    (float) (y1 - ((((velocityY / 40) * 27 * t) / 20)) + (0.5 * 9.8 * Math.pow((((27 * t) / 20)), 2))),
                    100,x+50,y+50)) {
                frameLayout.removeViewAt(1);
                frameLayout.addView(new PKMNdraw(this, this, drab, x, y, true), 1);
                caught = true;
            }
            frameLayout.addView(new Throdraw(this, this, x1, y1, velocityX / 40, velocityY / 40, hw, t), 2);
            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (caught) {
                            p.setCdt(new Date());
                            try {
                                String s = "";
                                FileOutputStream fileOutputStream = openFileOutput("PKMN",MODE_PRIVATE);
                                fileOutputStream.write(s.getBytes());
                                fileOutputStream.close();
                                Toast.makeText(MainActivity.this, p.getName() + " was caught!", Toast.LENGTH_SHORT).show();
                                recreate();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, p.getName() + " escaped!", Toast.LENGTH_SHORT).show();
                            sensorManager.registerListener(MainActivity.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
                            found = false;
                            retry = true;
                        }
                        frameLayout.removeViewAt(3);
                    }
                },5000);
            return true;
        }
        else
        {
            return false;
        }
    }



    double gett0(float vy, double a)
    {
        return ((vy*Math.abs(Math.sin(a)))/(9.8*Math.abs(Math.sin(a))));
    }

    double geta(float x1, float x2, float y1, float y2)
    {
        //double t = gett0(y1,y2,vy);
        Log.i("PROA",Double.toString(Math.atan((y2-y1)/(x2-x1))));
        return Math.atan((y2-y1)/(x2-x1));
    }

    boolean inball(float x1, float y1, float r, float x0, float y0)
    {
        return (Math.sqrt(Math.pow((y1-y0),2)+Math.pow((x1-x0),2))<r);
    }

}
