package me.ryansimon.playandchat.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ryan Simon
 */
public class Game {
    
    @SerializedName("name")
    private String mName;

    @SerializedName("game_image_url")
    private String mGameImageUrl;

    @SerializedName("rating")
    private String mRating;
    
    @SerializedName("rating_hex_color")
    private String mGameRatingHexColor;
    
    @SerializedName("last_played_date")
    private String mLastPlayedDate;

    public Game() {
        // needed for deserializing
    }
    
    /***** GETTERS AND SETTERS *****/
    
    public String getGameImageUrl() {
        return mGameImageUrl;
    }

    public void setGameImageUrl(String gameImageUrl) {
        mGameImageUrl = gameImageUrl;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getRating() {
        return mRating;
    }

    public void setRating(String rating) {
        mRating = rating;
    }

    public String getGameRatingHexColor() {
        return mGameRatingHexColor;
    }

    public void setGameRatingHexColor(String gameRatingHexColor) {
        mGameRatingHexColor = gameRatingHexColor;
    }

    public String getLastPlayedDate() {
        return mLastPlayedDate;
    }

    public void setLastPlayedDate(String lastPlayedDate) {
        mLastPlayedDate = lastPlayedDate;
    }
}
