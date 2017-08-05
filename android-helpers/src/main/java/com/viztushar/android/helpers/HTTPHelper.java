package com.viztushar.android.helpers;

import android.util.Pair;

import java.util.List;

/**
 * Created by HP on 05-08-2017.
 */

public class HTTPHelper {
    public static String createGetURL(String baseUrl, List<Pair<String, String>> params) {
        StringBuilder buffer = new StringBuilder();
        buffer.append(baseUrl);
        if (params.size() > 0)
            buffer.append("?");
        for (int i = 0; i < params.size(); i++) {
            buffer.append(params.get(i).first);
            buffer.append("=");
            buffer.append(params.get(i).second);
            if (i != params.size() - 1)
                buffer.append("&");
        }
        return buffer.toString();
    }
}
