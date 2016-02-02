package com.example.sam10795.pokemap;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Date;


public class Flaunch extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flaunch);
        SharedPreferences sharedPreferences = getSharedPreferences("PKDT",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Start",new Date().toString());
        EditText editText = (EditText)findViewById(R.id.editText);
        editor.putString("Name",editText.getText().toString());
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        editor.putString("ID",tm.getDeviceId());
        editor.putInt("Pokeballs",5);
        editor.putInt("Days",1);
        File file = new File(getFilesDir(),"PKMN");
        try {
            FileOutputStream fo = openFileOutput("PKMN",MODE_PRIVATE);
            String pkmndata = "Name + ; + Nick + ; + lv + ; + shiny + ; + ID + ; + id + ; + cdt + ; + bmp";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        editor.apply();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_flaunch, menu);
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
