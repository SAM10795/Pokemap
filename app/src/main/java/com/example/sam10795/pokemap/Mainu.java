package com.example.sam10795.pokemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class Mainu extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("PKDT", MODE_PRIVATE);
        SharedPreferences.Editor speditor = sharedPreferences.edit();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date d = new Date();
        try {
            d = df.parse(sharedPreferences.getString("Date", df.format(new Date())));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(new Date().getTime()-d.getTime()>=86400000)
        {
            speditor.putInt("Pokeballs",sharedPreferences.getInt("Pokeballs",0)+5);
            Toast.makeText(this,"You have 5 more Pokeballs now",Toast.LENGTH_SHORT).show();
            int days = sharedPreferences.getInt("Days",0);
            days+=1;
            if(days%7==0)
            {
                speditor.putInt("GBall",sharedPreferences.getInt("GBall",0)+5);
                Toast.makeText(this,"You have 5 more Great Balls now",Toast.LENGTH_SHORT).show();
            }
            if(days%30==0)
            {
                speditor.putInt("UBall",sharedPreferences.getInt("UBall",0)+5);
                Toast.makeText(this,"You have 5 more Ultra Balls now",Toast.LENGTH_SHORT).show();
            }
            if(days%365==0)
            {
                speditor.putInt("MBall",sharedPreferences.getInt("MBall",0)+5);
                Toast.makeText(this,"You have 5 more Master Balls now",Toast.LENGTH_SHORT).show();
            }
            speditor.putInt("Days",days);
            speditor.putString("Date",df.format(new Date()));
        }
        setContentView(R.layout.activity_mainu);
        LinearLayout l1 = (LinearLayout)findViewById(R.id.CMODE);
        l1.setOnClickListener(this);
        LinearLayout l2 = (LinearLayout)findViewById(R.id.MPKMN);
        l2.setOnClickListener(this);
        LinearLayout l3 = (LinearLayout)findViewById(R.id.MPROF);
        l3.setOnClickListener(this);
        LinearLayout l4 = (LinearLayout)findViewById(R.id.PKBUY);
        l4.setOnClickListener(this);
        LinearLayout l5 = (LinearLayout)findViewById(R.id.CPRAC);
        l5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Intent intent = new Intent();
        switch(id)
        {
            case R.id.CMODE:
            {
                intent = new Intent(Mainu.this,MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.CPRAC:
            {
                break;
            }
            case R.id.MPROF:
            {
                intent = new Intent(Mainu.this,Profile.class);
                startActivity(intent);
                break;
            }
            case R.id.MPKMN:
            {
                intent = new Intent(Mainu.this,mypkmn.class);
                startActivity(intent);
                break;
            }
            case R.id.PKBUY:
            {
                break;
            }
            default:
            {
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mainu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
