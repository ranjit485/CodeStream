package com.ranjit.App;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeVideoAdapter extends RecyclerView.Adapter<HomeVideoAdapter.ViewHolder> {

    private List<HomeDataModal> homeDataModalList;
    private ItemListener listener;

    public HomeVideoAdapter(List<HomeDataModal> homeDataModalList,ItemListener listener) {
        this.homeDataModalList = homeDataModalList;
        this.listener =listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_cardview, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeDataModal homeDataModal = homeDataModalList.get(position);

        Picasso.get()
                .load(homeDataModal.getPlaylistThumbnail()) // Assuming you have a method to get the URL of the thumbnail
                .placeholder(R.drawable.sample_thumbnail) // Optional placeholder image
                .error(R.drawable.sample_thumbnail) // Optional error image
                .into(holder.playlistThumbnail);

        // Load the second image similarly
        Picasso.get()
                .load(homeDataModal.getChannelLogo()) // Replace with the actual method to get the second image URL
                .placeholder(R.drawable.ic_baseline_whatshot_24) // Optional placeholder image
                .error(R.drawable.ic_baseline_whatshot_24) // Optional error image
                .into(holder.channelLogo);

        holder.playlistTitle.setText(homeDataModal.getPlaylistTitle());
        holder.channelName.setText(homeDataModal.getChannelName());
        holder.videoCount.setText(homeDataModal.getVideoCount());

        holder.playlistThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(homeDataModalList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return homeDataModalList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView channelLogo;
        ImageView playlistThumbnail;
        TextView channelName;
        TextView playlistTitle;
        TextView videoCount;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            playlistTitle = itemView.findViewById(R.id.textView_playlist_title);
            channelName = itemView.findViewById(R.id.textView_channel_name);
            videoCount = itemView.findViewById(R.id.textView_videos_count);
            channelLogo = itemView.findViewById(R.id.imageView_channel_logo);
            playlistThumbnail = itemView.findViewById(R.id.imageView_thumbnail);
            recyclerView = itemView.findViewById(R.id.homeRecylerView);

        }
    }
}