package me.ryansimon.playandchat.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Ryan Simon
 */
public class Profile {

    @SerializedName("name")
    private String mName;
    
    @SerializedName("photo_url")
    private String mPhotoUrl;
    
    @SerializedName("playchat_id")
    private String mPlayChatId;
    
    @SerializedName("background_image")
    private String mBackgroundImage;
    
    @SerializedName("location")
    private String mLocation;
    
    @SerializedName("flag_image")
    private String mFlagImage;

    /***** GETTERS AND SETTERS *****/
    
    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPhotoUrl() {
        return mPhotoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        mPhotoUrl = photoUrl;
    }

    public String getPlayChatId() {
        return mPlayChatId;
    }

    public void setPlayChatId(String playChatId) {
        mPlayChatId = playChatId;
    }

    public String getBackgroundImage() {
        return mBackgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        mBackgroundImage = backgroundImage;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getFlagImage() {
        return mFlagImage;
    }

    public void setFlagImage(String flagImage) {
        mFlagImage = flagImage;
    }
}
