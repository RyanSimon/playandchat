package me.ryansimon.playandchat.util;

import android.content.Context;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ryan Simon
 */
public class JsonUtil {

    public static String loadJsonFromAssets(Context context, String filePath, String fileName) {
        String json = null;

        try {
            InputStream is = context.getAssets().open(filePath + fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }
   
    public static String loadJsonFromExternal(String filePath, String fileName) {
        String json = null;
        
        try {
            InputStream is = new FileInputStream(filePath + "/" + fileName);

            int size = is.available();

            byte[] buffer = new byte[size];

            is.read(buffer);

            is.close();

            json = new String(buffer, "UTF-8");
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return json;
    }
}
