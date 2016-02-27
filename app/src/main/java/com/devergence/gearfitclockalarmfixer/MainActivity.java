package com.devergence.gearfitclockalarmfixer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void onClickSave(View v){

        try {

            int Interval = Integer.valueOf(((EditText) findViewById(R.id.etInterval)).getText().toString());
            int Repeats = Integer.valueOf(((EditText) findViewById(R.id.etRepeats)).getText().toString());

            if (Repeats > 100 || Repeats < 1 || Interval < 1) {
                throw new Exception("Invalid values)");
            }

            Preferences.SaveRepeats(this, Repeats);
            Preferences.SaveInterval(this, Interval);

        }catch(Exception ex) {
            Toast.makeText(this, getString(R.string.invalid_values_message), Toast.LENGTH_LONG);
        }

    }


    /** just for testing */

    public void onClickTestStop(View v){
        Log.i("AlarmClock", "Click stop");

        String msg = "com.devergence.gearfitclockalarmfixer.testStop";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Intent startIntent = new Intent(msg);
        PendingIntent startPIntent = PendingIntent.getBroadcast(this, 0, startIntent, 0);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, calendar.getTimeInMillis(), startPIntent);
    }

    public void onClickTestStart(View v){
        Log.i("AlarmClock", "Click start");

        String msg = "com.devergence.gearfitclockalarmfixer.testStart";

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        Intent startIntent = new Intent(msg);
        PendingIntent startPIntent = PendingIntent.getBroadcast(this, 0, startIntent, 0);
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, calendar.getTimeInMillis(), startPIntent);
    }

}
