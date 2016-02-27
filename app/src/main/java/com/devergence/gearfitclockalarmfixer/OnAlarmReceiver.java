package com.devergence.gearfitclockalarmfixer;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Random;

/**
 * Created by findemor on 26/08/2015.
 */
public class OnAlarmReceiver extends BroadcastReceiver {

    //http://stackoverflow.com/questions/4115649/listing-of-manufacturers-clock-alarm-package-and-class-name-please-add

    String[] ENABLE_ALARM_EVENTS = new String[]
            {
                    "com.devergence.gearfitclockalarmfixer.testStart",
                    "com.google.android.deskclock.ALARM_ALERT",
                    "com.android.deskclock.ALARM_ALERT"
            };

    String[] DISABLE_ALARM_EVENTS = new String[]
            {
                    "com.devergence.gearfitclockalarmfixer.testStop",
                    "com.google.android.deskclock.ALARM_SNOOZE",
                    "com.google.android.deskclock.ALARM_DISMISS",
                    "com.google.android.deskclock.ALARM_DONE",
                    "com.android.deskclock.ALARM_SNOOZE",
                    "com.android.deskclock.ALARM_DISMISS",
                    "com.android.deskclock.ALARM_DONE",
            };

    static String LOCAL_EVENT_START = "com.devergence.gearfitclockalarmfixer.startAlarm";


    static int TimesToNotifyLeft = 0;


    @Override
    public void onReceive(Context ctx, Intent i) {
        String action = i.getAction();

        //Toast.makeText(ctx, action, Toast.LENGTH_LONG).show();
        Log.i("AlarmClock", action);

        if (action.equals(LOCAL_EVENT_START)) {
            //evento local
            notifyAlarm(ctx);
        }

        for (String enableEvent : ENABLE_ALARM_EVENTS) {
            if (action.equals(enableEvent))
            {
                //Toast.makeText(ctx, "ACTIVAR", Toast.LENGTH_LONG).show();
                Log.i("AlarmClock", "ACTIVAR");
                startAlarm(ctx, Preferences.GetInterval(ctx));
                break;
            }
        }

        for (String disableEvent : DISABLE_ALARM_EVENTS) {
            if (action.equals(disableEvent))
            {
                //Toast.makeText(ctx, "DESACTIVAR", Toast.LENGTH_LONG).show();
                Log.i("AlarmClock", "DESACTIVAR");
                stopAlarm();
                break;
            }
        }
    }

    private Calendar getNextAlarmDate(int seconds){
        // Set the alarm to start at approximately 2:00 p.m.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, seconds);
        return calendar;
    }

    private void notifyAlarm(Context ctx){

        if (TimesToNotifyLeft > 0){

            Log.i("AlarmClock", "NOTIFICACION! (Times left: " + TimesToNotifyLeft + ")");

            TimesToNotifyLeft = TimesToNotifyLeft - 1;

            Notification.Builder notif = getDefaultNotification(ctx);

            //Random random = new Random();
            //int NotificationId = random.nextInt(1000000);

            int NotificationId = 1000000;

            NotificationManager notificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NotificationId, notif.build());

            programNextNotification(ctx, Preferences.GetInterval(ctx));
        }else
        {
            Log.i("AlarmClock", "No Notif (Times left: " + TimesToNotifyLeft + ")");
        }
    }

    private android.app.Notification.Builder getDefaultNotification(Context ctx) {

        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setSmallIcon(R.drawable.ic_launcher)
                .setWhen(System.currentTimeMillis())
                .setContentTitle(ctx.getString(R.string.notif_title))
                .setContentText(ctx.getString(R.string.notif_message));

        //return builder.build(); //A partir de Jelly Bean se usa éste método-
        return builder; //Antes de jelly bean -> .getNotification();
    }

    private void startAlarm(Context ctx, int interval)
    {
        TimesToNotifyLeft = Preferences.GetRepeats(ctx);
        programNextNotification(ctx, interval);
    }

    private void programNextNotification(Context ctx, int interval) {
        //preparamos la notificacion
        Intent startIntent = new Intent(LOCAL_EVENT_START);
        PendingIntent startPIntent = PendingIntent.getBroadcast(ctx, 0, startIntent, 0);
        AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        alarm.set(AlarmManager.RTC, getNextAlarmDate(interval).getTimeInMillis(), startPIntent);

        Log.i("AlarmClock", "Alarm set (Times left: " + TimesToNotifyLeft+ ")");
    }

    private void stopAlarm()
    {
        TimesToNotifyLeft = 0;
        Log.i("AlarmClock", "Alarm stopped (Times left: " + TimesToNotifyLeft+ ")");
    }

}
