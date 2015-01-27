package me.ryansimon.playandchat;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.ryansimon.playandchat.api.model.Game;

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
    }

    @Override
    public int getItemCount() {
        return (mGameList != null) ? mGameList.size() : 0;
    }
    
    public static class GameViewHolder extends RecyclerView.ViewHolder {
        
        GameViewHolder(View itemView) {
            super(itemView);
        }
    }
    
}
