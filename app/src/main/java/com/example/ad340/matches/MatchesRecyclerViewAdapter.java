package com.example.ad340.matches;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ad340.R;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.function.Consumer;

public class MatchesRecyclerViewAdapter extends RecyclerView.Adapter<MatchesViewHolder> {

    private List<Match> matchesList;
    private Consumer<Match> onClickCallback;

    MatchesRecyclerViewAdapter(List<Match> matchesList, Consumer<Match> onClickCallback) {
        this.matchesList = matchesList;
        this.onClickCallback = onClickCallback;
    }

    @NonNull
    @Override
    public MatchesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        return new MatchesViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull MatchesViewHolder holder, int position) {
        if (matchesList != null && position < matchesList.size()) {
            Match match = matchesList.get(position);
            holder.matchesName.setText(match.getName());
            Picasso.get().load(match.getImageUrl()).into(holder.matchesImage);
            if (match.isLiked()) {
                holder.likeButton.setImageResource(R.drawable.heart_icon_filled);
            } else {
                holder.likeButton.setImageResource(R.drawable.heart_icon);
            }
            holder.likeButton.setOnClickListener((v) -> {
                Toast.makeText(v.getContext(),
                        String.format(v.getContext().getString(R.string.liked_message),
                                match.getName()), Toast.LENGTH_LONG).show();
                onClickCallback.accept(match);
            });
        }
    }

    @Override
    public int getItemCount() {
        return matchesList.size();
    }
}
