package me.ryansimon.playandchat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import me.ryansimon.playandchat.api.model.Game;
import me.ryansimon.playandchat.widget.TypefaceTextView;

/**
 * @author Ryan Simon
 */
public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<Game> mGameList;

    public GameAdapter(List<Game> gameList) {
        mGameList = gameList;
    }

    @Override
    public GameViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_list_item, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GameViewHolder holder, int position) {

        // set our Views
        Game game = mGameList.get(position);

        Picasso.with(holder.getGameImage().getContext())
                .load(game.getGameImageUrl())
                .into(holder.getGameImage());
        
        holder.getGameName().setText(game.getName());
        
        holder.getGameRating().setText(game.getRating());
        holder.getGameRating().getBackground().setColorFilter(
                Color.parseColor(game.getGameRatingHexColor()),
                PorterDuff.Mode.MULTIPLY
        );
        
        holder.getLastPlayedDate().setText(game.getLastPlayedDate());
    }

    @Override
    public int getItemCount() {
        return (mGameList != null) ? mGameList.size() : 0;
    }
    
    public static class GameViewHolder extends RecyclerView.ViewHolder {

        /**
         * Layout vars 
         */
        private CircleImageView mGameImage;
        private TypefaceTextView mGameName;
        private TypefaceTextView mGameRating;
        private TypefaceTextView mLastPlayedDate;
        
        GameViewHolder(final View itemView) {
            super(itemView);
            
            // get layout vars
            mGameImage = (CircleImageView) itemView.findViewById(R.id.game_image);
            mGameName = (TypefaceTextView) itemView.findViewById(R.id.game_name);
            mGameRating = (TypefaceTextView) itemView.findViewById(R.id.game_rating);
            mLastPlayedDate = (TypefaceTextView) itemView.findViewById(R.id.last_played_date);
            
            // point to the mock chess board
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(new Intent(v.getContext(),GameActivity.class));
                    Activity activity = (Activity) v.getContext();
                    activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                }
            });
        }

        /***** GETTER AND SETTERS *****/
        
        public CircleImageView getGameImage() {
            return mGameImage;
        }

        public void setGameImage(CircleImageView gameImage) {
            mGameImage = gameImage;
        }

        public TypefaceTextView getLastPlayedDate() {
            return mLastPlayedDate;
        }

        public void setLastPlayedDate(TypefaceTextView lastPlayedDate) {
            mLastPlayedDate = lastPlayedDate;
        }

        public TypefaceTextView getGameRating() {
            return mGameRating;
        }

        public void setGameRating(TypefaceTextView gameRating) {
            mGameRating = gameRating;
        }

        public TypefaceTextView getGameName() {
            return mGameName;
        }

        public void setGameName(TypefaceTextView gameName) {
            mGameName = gameName;
        }
    }
    
}
