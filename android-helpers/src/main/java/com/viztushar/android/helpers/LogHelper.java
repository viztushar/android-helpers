package com.viztushar.android.helpers;

import android.util.Log;

/**
 * Created by Tushar on 29-07-2017.
 */

public class LogHelper {

    public static void getDebugLog(String msg) {
        if (BuildConfig.DEBUG)
            Log.e("DEBUG-TAG", msg);
    }

    public static void getLog(String tag,String msg) {
        Log.e(tag, msg);
    }
}
