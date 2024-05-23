package com.example.ramanmishraproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.List;

public class RedditPostAdapter extends RecyclerView.Adapter<RedditPostAdapter.RedditPostViewHolder> {

    private List<RedditPost> redditPosts;
    private Context context;

    public RedditPostAdapter(List<RedditPost> redditPosts, Context context) {
        this.redditPosts = redditPosts;
        this.context = context;
    }

    @NonNull
    @Override
    public RedditPostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reddit_post, parent, false);
        return new RedditPostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RedditPostViewHolder holder, int position) {
        RedditPost redditPost = redditPosts.get(position);
        holder.titleTextView.setText(redditPost.getTitle());

        // Use Glide to load the image
        Glide.with(context)
                .load(redditPost.getUrl())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return redditPosts.size();
    }

    static class RedditPostViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        ImageView imageView;

        public RedditPostViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            imageView = itemView.findViewById(R.id.postImageView);
        }
    }
}
