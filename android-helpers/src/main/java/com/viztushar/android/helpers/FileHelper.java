package com.viztushar.android.helpers;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.OpenableColumns;
import android.util.Pair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Tushar on 29-07-2017.
 */

public class FileHelper {

    public static String getFileName(Context context, Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    public static Uri getUriOfMedia(String albumId) {
        return ContentUris.withAppendedId(Uri.parse("content://media/external/audio/albumart"),
                Long.parseLong(albumId));
    }

    public static String md5(final String string) {
        String s = string + "332nSaltnfj";
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

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
