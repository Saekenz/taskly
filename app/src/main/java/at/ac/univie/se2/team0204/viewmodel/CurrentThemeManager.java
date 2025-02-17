package at.ac.univie.se2.team0204.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class CurrentThemeManager {

    private static final CurrentThemeManager manager= new CurrentThemeManager();
    private boolean nightMode;

    private static SharedPreferences sP;
    //SharedPreferences.Editor editor;

    private CurrentThemeManager(){

    }

    public static boolean initAndGetInformation(Context context){
        if(sP == null)
            sP = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);

        return sP.getBoolean("NightMode", false);
    }

    public static void writeNightModeStatus(boolean value) {
        SharedPreferences.Editor prefsEditor = sP.edit();
        prefsEditor.putBoolean("NightMode", value);
        prefsEditor.commit();
    }




    public static CurrentThemeManager getManager() {
        return manager;
    }

    public boolean nightModeActive() {
        return nightMode;
    }

    public void setNightMode(boolean isActivated) {
        if(isActivated)
            nightMode=true;
        else
            nightMode=false;
    }
}
