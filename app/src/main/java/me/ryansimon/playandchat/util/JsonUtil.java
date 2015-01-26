package me.ryansimon.playandchat.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ryan Simon
 */
public class JsonUtil {
    
    private static final String FILE_PATH = "json/";
    
    public static String loadJsonFromFile(Context context) {
        String json = null;
        
        try {
            InputStream is = context.getAssets().open(FILE_PATH + "profile.json");

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
