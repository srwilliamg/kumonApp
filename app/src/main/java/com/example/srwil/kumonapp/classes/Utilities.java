package com.example.srwil.kumonapp.classes;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.srwil.kumonapp.R;
import com.example.srwil.kumonapp.constants.GlobalVars;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Utilities {
    public static String TAG;

    public static void getFirebaseToken(){
        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                @Override
                public void onComplete(@NonNull Task<InstanceIdResult> task) {
                    if (!task.isSuccessful()) {
                        Log.w(TAG, "getInstanceId failed", task.getException());
                        return;
                    }

                    // Get new Instance ID token
                    String token = task.getResult().getToken();
                    GlobalVars.setFcmToken(token);

                    // Log and toast
                    String msg = R.string.token_message + token;
                    Log.i(TAG, msg);
                }
            });
    }

    public static String getDateStringFromDateTime(String dateString) {
    String formattedDate = dateString;
        DateTimeFormatter inputFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyy", Locale.ENGLISH);
            LocalDate date = LocalDate.parse(dateString, inputFormatter);
            formattedDate = outputFormatter.format(date);
        }
        return formattedDate;
    }
}
