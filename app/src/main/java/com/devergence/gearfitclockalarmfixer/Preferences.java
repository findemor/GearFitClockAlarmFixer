package com.devergence.gearfitclockalarmfixer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by findemor on 27/02/16.
 */
public class Preferences {


    static final int NOTIFICATION_TIMES = 3;
    static final int NOTIFICATION_SECONDS_INTERVAL = 3;

    static final String KEY_PREFS = "gear_fit_preferences";
    static final String KEY_REPEATS = "REPEATS";
    static final String KEY_INTERVAL = "INTERVAL";

    public static void SaveRepeats(Context ctx, int repeats){
        SharedPreferences sharedPref = ctx.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(KEY_REPEATS, repeats);
        editor.commit();
    }

    public static int GetRepeats(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        return sharedPref.getInt(KEY_REPEATS, NOTIFICATION_TIMES);
    }

    public static void SaveInterval(Context ctx, int interval){
        SharedPreferences sharedPref = ctx.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(KEY_INTERVAL, interval);
        editor.commit();
    }

    public static int GetInterval(Context ctx) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(KEY_PREFS, Context.MODE_PRIVATE);
        return sharedPref.getInt(KEY_INTERVAL, NOTIFICATION_SECONDS_INTERVAL);
    }

}
