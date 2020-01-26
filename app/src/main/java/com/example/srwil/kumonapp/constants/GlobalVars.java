package com.example.srwil.kumonapp.constants;

import android.content.Context;

public class GlobalVars {

    private static final GlobalVars ourInstance = new GlobalVars();

    private static String fbToken;
    private static Context mainContext;

    public static String getFbToken() {
        return fbToken;
    }

    public static void setFbToken(String fbToken) {
        GlobalVars.fbToken = fbToken;
    }

    static GlobalVars getInstance() {
        return ourInstance;
    }

    public static Context getMainContext() {
        return mainContext;
    }

    public static void setMainContext(Context mainContext) {
        GlobalVars.mainContext = mainContext;
    }

    private GlobalVars() {}
}
