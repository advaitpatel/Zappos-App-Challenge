package com.ilovenougat.classes.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Advait on 9/10/2016.
 */
public class Utils {

    public static boolean Online(Context context) {
        //Create object for ConnectivityManager class which returns network related info
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //If connectivity object is not null
        if (connectivity != null) {
            //Get network info - Mobile internet access
            NetworkInfo mobile_net = connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            NetworkInfo wifi = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);


            if (mobile_net != null && wifi != null) {
                //Look for whether device is currently connected to Mobile internet
                if (mobile_net.isConnected() || wifi.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isDefined(String str) {
        if(str != null && !str.equals("")) {
            return true;
        }
        else {
            return false;
        }
    }
}
