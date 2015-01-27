package me.ryansimon.playandchat.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ryan Simon
 */
public class Game {
    
    @SerializedName("name")
    private String mName;

    @SerializedName("game_image_url")
    private String mGameImage;

    @SerializedName("rating")
    private String mRating;
    
    @SerializedName("rating_hex_color")
    private String mGameRatingHexColor;
    
    @SerializedName("last_played_date")
    private String mLastPlayedDate;

    /***** GETTERS AND SETTERS *****/
    
    public String getGameImage() {
        return mGameImage;
    }

    public void setGameImage(String gameImage) {
        mGameImage = gameImage;
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
