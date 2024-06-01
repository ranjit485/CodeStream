package com.ranjit.App;

import static com.android.volley.VolleyLog.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoPlayer extends Fragment {

    private List<VideosModal> videosModalList;
    private RecyclerView videosRecyclerView;
    private VideosAdapter videosAdapter;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_video_player, container, false);
        // Initialize the list and RecyclerView
        videosModalList = new ArrayList<>();
        getHomeDataPlaylistData();
        videosRecyclerView = view.findViewById(R.id.videolist_recycle_view);
        videosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        videosAdapter = new VideosAdapter(videosModalList);
        videosRecyclerView.setAdapter(videosAdapter);
        YouTubePlayerView youTubePlayerView = view.findViewById(R.id.player_view);

        getLifecycle().addObserver(youTubePlayerView);

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "ABTdTTnnEU8";
                youTubePlayer.loadVideo(videoId, 0);
            }
        });

        return view;
    }

    private void getHomeDataPlaylistData() {
        String url = "https://codestreamserver.onrender.com/playlist?playlist_id=PLu0W_9lII9ahR1blWXxgSlL4y9iQBnLpR";

        Log.d(TAG, "Request URL: " + url);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
                        Log.d(TAG, "Response JSON: " + response.toString());

                        try {
                            // The response is directly a JSONObject containing all playlist details
                            String playlistId = response.getString("playlist_id");
                            String playlistOwner = response.getString("creator");
                            String playlistDescription = response.getString("description");
                            String playlistThumbnail = response.getString("thumbnail");
                            String playlistTitle = response.getString("playlist_title");
                            int videoCount = response.getInt("video_count");

                            JSONArray videosArray = response.getJSONArray("videos");

                            for (int i = 0; i < videosArray.length(); i++) {
                                JSONObject videoDetailObject = videosArray.getJSONObject(i);

                                // Map JSON fields to the corresponding fields in VideosModal
                                String videoID = videoDetailObject.getString("id");
                                String videoThumbnail = videoDetailObject.getString("thumbnail");
                                String videoTitle = videoDetailObject.getString("title");
                                String videoDescription = videoDetailObject.getString("description");
                                String videoUrl = videoDetailObject.getString("url");
                                String videoDuration = videoDetailObject.getString("duration");

                                // Create an instance of VideosModal
                                VideosModal videosModal = new VideosModal(
                                        playlistId,
                                        playlistOwner,
                                        playlistDescription,
                                        playlistThumbnail,
                                        playlistTitle,
                                        String.valueOf(videoCount), // Convert int to String
                                        videoID,
                                        videoThumbnail,
                                        videoTitle,
                                        videoDescription,
                                        videoUrl,
                                        videoDuration
                                );

                                // Add the VideosModal instance to the list
                                videosModalList.add(videosModal);

                            }

                            Log.d(TAG, "Videos Size: " + videosModalList.size());

                            // Notify adapter of data change
                            videosAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error while fetching API", Toast.LENGTH_LONG).show();
                        Log.e(TAG, "Error: " + error.getMessage());
                    }
                });


        // Add the request to the RequestQueue.
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }


}
