package me.ryansimon.playandchat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;
import me.ryansimon.playandchat.api.model.Profile;
import me.ryansimon.playandchat.util.JsonUtil;
import me.ryansimon.playandchat.widget.TypefaceButton;
import me.ryansimon.playandchat.widget.TypefaceTextView;

/**
 * @author Ryan Simon
 */
public class ProfileActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupToolbar();
        setupProfileUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    
    /***** HELPER METHODS *****/

    /**
     * Sets up Toolbar and the title of the Activity
     */
    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        
        // now that we set the ActionBar, we need to edit it
        ActionBar actualActionBar = getSupportActionBar();
        actualActionBar.setHomeAsUpIndicator(getResources().getDrawable(R.drawable.back_button));
        actualActionBar.setTitle("");
    }
    
    private void setupProfileUI() {
        Gson gson = new Gson();
        Profile profile = gson.fromJson(JsonUtil.loadJsonFromFile(this),Profile.class);

        // get layout vars
        TypefaceTextView userName = (TypefaceTextView) findViewById(R.id.name);
        TypefaceTextView profileId = (TypefaceTextView) findViewById(R.id.profile_id);
        TypefaceTextView location = (TypefaceTextView) findViewById(R.id.location);
        ImageView userCountryFlag = (ImageView) findViewById(R.id.user_country_flag);
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
        TypefaceButton editProfile = (TypefaceButton) findViewById(R.id.edit_profile_btn);

        // load content
        userName.setText(profile.getName());
        
        profileId.setText("(" + profile.getPlayChatId() + ")");
        
        location.setText(profile.getLocation());
        
        Picasso.with(this).load(profile.getFlagImage())
                .into(userCountryFlag);
        
        Picasso.with(this).load(profile.getBackgroundImage())
                .into(backgroundImage);

        Picasso.with(this).load(profile.getPhotoUrl())
                .into(profileImage);
        
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setMessage(R.string.edit_dialog_message)
                        .setPositiveButton(R.string.edit_dialog_confimation, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
    }
}
