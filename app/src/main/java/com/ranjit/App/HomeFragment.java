package com.ranjit.App;

import static com.android.volley.VolleyLog.TAG;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements ItemListener {

    private List<HomeDataModal> homeDataModalArrayList;
    private RecyclerView homeRecyclerView;
    private HomeVideoAdapter homeVideoAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // Initialize the list and RecyclerView
        homeDataModalArrayList = new ArrayList<>();
        homeRecyclerView = rootView.findViewById(R.id.homeRecylerView);
        homeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        homeVideoAdapter = new HomeVideoAdapter(homeDataModalArrayList,this);
        homeRecyclerView.setAdapter(homeVideoAdapter);

        // Fetch data
        getHomeDataPlaylistData();

        return rootView;
    }

    private void getHomeDataPlaylistData(){
        String url = "https://api.jsonbin.io/v3/b/66500c29acd3cb34a84cab51";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the JSON response
                        Log.d(TAG, "My Respone Json  : " + response);

                        try {
                            JSONObject recordObject = response.getJSONObject("record");
                            JSONArray videosArray = recordObject.getJSONArray("videos");

                            for (int i = 0; i < videosArray.length(); i++) {
                                JSONObject jsonObject = videosArray.getJSONObject(i);

                                // Assuming the JSON response structure matches the POJO fields
                                String id = jsonObject.getString("id");
                                String playlistUid = jsonObject.getString("videoCount");
                                String playlistTitle = jsonObject.getString("playlistName");
                                String playlistThumbnail = jsonObject.getString("playlistThumbnail");
                                String channelName = jsonObject.getString("channelName");
                                String channelLogo = jsonObject.getString("channelLogo");
                                String channelUrl = jsonObject.getString("channelUrl");

                                // Create an instance of MyData
                                HomeDataModal homeDataModal = new HomeDataModal(id, playlistUid, playlistTitle, playlistThumbnail, channelName, channelLogo, channelUrl);

                                // Add the MyData instance to the list
                                homeDataModalArrayList.add(homeDataModal);
                            }
                            Log.d(TAG, "My HomeDataModalArray : " + homeDataModalArrayList.size());

                            // Notify the adapter that data has changed
                            homeVideoAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Error while fetching API",Toast.LENGTH_LONG).show();
                    }
                });

        // Access the RequestQueue through your singleton class
        MySingleton.getInstance(getActivity().getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClick(HomeDataModal homeDataModal) {
        Toast.makeText(getContext(),"Reycleer clicked"+homeDataModal.channelName,Toast.LENGTH_LONG).show();
        getHomeDataPlaylistData();

        loadFragment(new VideoPlayer());

    }
    private void loadFragment(Fragment fragment) {
// create a FragmentManager
        FragmentManager fm = getFragmentManager();
// create a FragmentTransaction to begin the transaction and replace the Fragment
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
// replace the FrameLayout with new Fragment
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit(); // save the changes
    }
}
