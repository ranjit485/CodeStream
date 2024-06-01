package com.ranjit.App;

import static com.android.volley.VolleyLog.TAG;

import android.nfc.Tag;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.ViewHolder> {
    private List<VideosModal> videosModalList;
    private ItemListener listener;

    public VideosAdapter(List<VideosModal> videosModalList) {
        this.videosModalList = videosModalList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.videos_list, parent, false);
        return new VideosAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        VideosModal videosModal = videosModalList.get(position);

        holder.videoDuration.setText(videosModal.getVideoDuration());
        holder.videoTitle.setText(videosModal.getVideoTitle());

        Log.d(TAG, "Video ID: " + videosModal.getVideoID());
        Log.d(TAG, "Video Title: " + videosModal.getVideoTitle());

    }

    @Override
    public int getItemCount() {
        return videosModalList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView videoDuration;
        TextView videoTitle;
        ImageView playButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            videoDuration = itemView.findViewById(R.id.videoDuration);
            videoTitle = itemView.findViewById(R.id.videoTitle);
            playButton = itemView.findViewById(R.id.list_play_button);
        }
    }

}
