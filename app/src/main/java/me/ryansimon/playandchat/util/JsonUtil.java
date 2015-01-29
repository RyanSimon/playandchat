package me.ryansimon.playandchat.util;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Ryan Simon
 */
public class JsonUtil {
    
    /**
     * Converts a JSON file stored in an app's assets folder to an Object of type 
     * typeToken.getType()
     *
     * @param context; current app context
     * @param typeToken; represents the JSON data model type
     * @param filePath; the path to the JSON file
     * @param fileName; the name of the JSON file
     * @return an Object of the type typeToken.getType()
     */
    public static Object loadJsonFromAssets(Context context, TypeToken<?> typeToken, String filePath, String fileName) {
        Object newJsonModel;

        try {
            BufferedReader fileReader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(filePath + fileName))
            );
            Gson gson = new Gson();
            newJsonModel = gson.fromJson(fileReader,typeToken.getType());
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return newJsonModel;
    }

    /**
     * Converts a JSON file in external storage into the appropriate Object the JSON data 
     * is modelled after
     *  
     * @param typeToken; a generic TypeToken that represents the JSON data model
     * @param filePath; the path to the JSON file
     * @param fileName; the name of the JSON file
     * @return an Object of the type typeToken.getType()
     */
    public static Object loadJsonFromExternal(TypeToken<?> typeToken, String filePath, String fileName) {

        Object newJsonModel;
        
        try {
            FileReader fileReader = new FileReader(filePath + "/" + fileName);
            Gson gson = new Gson();
            newJsonModel = gson.fromJson(fileReader,typeToken.getType());
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        
        return newJsonModel;
    }
}
