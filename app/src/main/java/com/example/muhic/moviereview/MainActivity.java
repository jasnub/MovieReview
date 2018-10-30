package com.example.muhic.moviereview;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements adapter.OnItemClickListener {
    public static final String EXTRA_URL = "imageUrl";
    public static final String EXTRA_TITLE = "title";
    public static final String EXTRA_OVERVIEW = "overview";
    public static final String EXTRA_URL2 = "imageUrl2";
    public static final String EXTRA_AVERAGE = "average";
    public static final String EXTRA_RELEASE = "release";


    private RecyclerView mRecyclerView;
    private adapter mAdapter;
    private ArrayList<item> mExampleList;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson(){
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=615fdfae5e8cce941c8817c6c79ffd74";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String title = hit.getString("title");
                                String overview = hit.getString("overview");
                                String average = hit.getString("vote_average");
                                String imagePath = hit.getString("poster_path");
                                String imagePath2 = hit.getString("backdrop_path");
                                String imageUrl = "http://image.tmdb.org/t/p/w185" + imagePath;
                                String imageUrl2 = "http://image.tmdb.org/t/p/w185" + imagePath2;
                                String release = hit.getString("release_date");



                                mExampleList.add(new item(imageUrl, title, overview, average, imageUrl2, release));
                            }

                            mAdapter = new adapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mAdapter);
                            mAdapter.setOnItemClickListener(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }


    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        item clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getmTitle());
        detailIntent.putExtra(EXTRA_OVERVIEW, clickedItem.getmOverview());
        detailIntent.putExtra(EXTRA_URL2, clickedItem.getmImageURL2());
        detailIntent.putExtra(EXTRA_AVERAGE, clickedItem.getmAverage());
        detailIntent.putExtra(EXTRA_RELEASE, clickedItem.getmRelease());


        startActivity(detailIntent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.language) {
            Toast.makeText(this,"English", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        } else {
            return super.onOptionsItemSelected(item);
        }

        return true;
    }
}

