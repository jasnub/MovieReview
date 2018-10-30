package com.example.muhic.moviereview;

public class item {
    private String mImageURL;
    private String mTitle;
    private String mOverview;
    private String mImageURL2;
    private String mAverage;
    private String mRelease;


    public item(String imageUrl, String title, String overview, String average, String imageUrl2, String release){
        mImageURL = imageUrl;
        mTitle = title;
        mOverview = overview;
        mImageURL2 = imageUrl2;
        mAverage = average;
        mRelease = release;

    }

    public String getmImageUrl() { return mImageURL; }

    public String getmTitle() { return mTitle; }

    public String getmOverview() { return mOverview; }

    public String getmImageURL2() { return mImageURL2; }

    public String getmAverage() { return mAverage; }

    public String getmRelease() { return mRelease; }

}
