package me.ryansimon.playandchat;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.ryansimon.playandchat.api.model.Game;
import me.ryansimon.playandchat.api.model.Profile;
import me.ryansimon.playandchat.util.JsonUtil;
import me.ryansimon.playandchat.widget.TypefaceButton;
import me.ryansimon.playandchat.widget.TypefaceTextView;

/**
 * @author Ryan Simon
 */
public class ProfileActivity extends ActionBarActivity {

    /**
     * Layout vars 
     */
    private RecyclerView mGameListView;
    
    private static final String PROFILE_URL = "http://prototype.playchat.net/test/profile.json";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupToolbar();
        
        // setup our RecyclerView
        mGameListView = (RecyclerView) findViewById(R.id.game_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mGameListView.setLayoutManager(llm);
        
        new DownloadFileFromURL().execute(PROFILE_URL);
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
        Profile profile = gson.fromJson(
                JsonUtil.loadJsonFromExternal(Environment.getExternalStorageDirectory().toString(),
                        "profile.json"),
                Profile.class
        );

        // get layout vars
        TypefaceTextView userName = (TypefaceTextView) findViewById(R.id.name);
        TypefaceTextView profileId = (TypefaceTextView) findViewById(R.id.profile_id);
        TypefaceTextView location = (TypefaceTextView) findViewById(R.id.location);
        ImageView userCountryFlag = (ImageView) findViewById(R.id.user_country_flag);
        ImageView backgroundImage = (ImageView) findViewById(R.id.background_image);
        CircleImageView profileImage = (CircleImageView) findViewById(R.id.profile_image);
        View editProfile = findViewById(R.id.edit_profile_btn);

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
        
        setupGameList();
    }
    
    private void setupGameList() {
        // get items
        Gson gson = new Gson();
        List<Game> gameList = gson.fromJson(JsonUtil.loadJsonFromAssets(this,"json/","games.json"),
                new TypeToken<List<Game>>(){}.getType());

        mGameListView.setAdapter(new GameAdapter(gameList));
    }

    /**
     * Downloads a file at the given URL string, and shows a ProgressDialog during the download
     * 
     * Adapted from http://stackoverflow.com/questions/15758856/android-how-to-download-file-from-webserver
     */
    private class DownloadFileFromURL extends AsyncTask<String, String, String> {

        private ProgressDialog mProfileFileProgressDialog;
        
        private void setupProfileDownloadDialog() {
            mProfileFileProgressDialog = new ProgressDialog(ProfileActivity.this);
            mProfileFileProgressDialog.setMessage("Downloading Profile Information. Please wait...");
            mProfileFileProgressDialog.setIndeterminate(false);
            mProfileFileProgressDialog.setMax(100);
            mProfileFileProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProfileFileProgressDialog.setCancelable(true);
            mProfileFileProgressDialog.show();
        }
        
        /**
         * Before starting background thread Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            setupProfileDownloadDialog();
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection connection = url.openConnection();
                connection.connect();

                // we'll use this to calculate our progress
                int lengthOfFile = connection.getContentLength();

                // download the file
                InputStream input = new BufferedInputStream(url.openStream(),
                        8192);

                // output file
                OutputStream output = new FileOutputStream(Environment
                        .getExternalStorageDirectory().toString()
                        + "/profile.json");

                byte data[] = new byte[1024];

                long total = 0;

                // read in our content
                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lengthOfFile));
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            mProfileFileProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            mProfileFileProgressDialog.dismiss();
            setupProfileUI();
        }
    }
}
