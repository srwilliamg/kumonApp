package com.example.srwil.kumonapp.constants;

import android.content.Context;

public class GlobalVars {

    private static final GlobalVars ourInstance = new GlobalVars();

    private static String idParent;
    private static String email;
    private static String name;
    private static String fcmToken;
    private static String urlTest = "https://kumonserver.herokuapp.com/api/";//"http://192.168.0.3:5000/api/"; //
    private static String urlAssets = "https://kumonserver.herokuapp.com/";//"http://192.168.0.3:5000/api/"; //
    private static String jwtToken;
    private static Context mainContext;

    public static String getFcmToken() {
        return fcmToken;
    }

    public static void setFcmToken(String fbToken) {
        GlobalVars.fcmToken = fbToken;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        GlobalVars.name = name;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        GlobalVars.email = email;
    }

    public static String getIdParent() {
        return idParent;
    }

    public static void setIdParent(String idParent) {
        GlobalVars.idParent = idParent;
    }

    public static String getUrlTest() {
        return urlTest;
    }

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setJwtToken(String jwtToken) {
        GlobalVars.jwtToken = jwtToken;
    }

    public static String getUrlAssets() {
        return urlAssets;
    }

    public static void setUrlAssets(String urlAssets) {
        GlobalVars.urlAssets = urlAssets;
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
