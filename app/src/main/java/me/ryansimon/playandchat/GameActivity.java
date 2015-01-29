package me.ryansimon.playandchat;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import me.ryansimon.playandchat.R;

public class GameActivity extends ActionBarActivity {

    /***** ACTIVITY LIFECYCLE METHODS *****/
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        
        setupToolbar();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == android.R.id.home) {
            startActivity(new Intent(this,ProfileActivity.class));
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        }
        else if (id == R.id.action_settings) {
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
}
