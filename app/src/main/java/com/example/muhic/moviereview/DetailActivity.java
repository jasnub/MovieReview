package com.example.muhic.moviereview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.muhic.moviereview.MainActivity.EXTRA_AVERAGE;
import static com.example.muhic.moviereview.MainActivity.EXTRA_OVERVIEW;
import static com.example.muhic.moviereview.MainActivity.EXTRA_RELEASE;
import static com.example.muhic.moviereview.MainActivity.EXTRA_TITLE;
import static com.example.muhic.moviereview.MainActivity.EXTRA_URL2;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        //String imageUrl = intent.getStringExtra(EXTRA_URL);
        String imageUrl2 = intent.getStringExtra(EXTRA_URL2);
        String title = intent.getStringExtra(EXTRA_TITLE);
        String overview = intent.getStringExtra(EXTRA_OVERVIEW);
        String average = intent.getStringExtra(EXTRA_AVERAGE);
        String release = intent.getStringExtra(EXTRA_RELEASE);

        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewTitle = findViewById(R.id.txt_title_detail);
        TextView textViewOverview = findViewById(R.id.txtOverview);
        TextView textAverage = findViewById(R.id.textAverage);
        TextView textRelease = findViewById(R.id.textRelease);

        Picasso.with(this).load(imageUrl2).fit().centerCrop().into(imageView);
        textViewTitle.setText(title);
        textViewOverview.setText(overview);
        textAverage.setText("Rating : "+ average);
        textRelease.setText("Release Date : " + release);
    }
}
