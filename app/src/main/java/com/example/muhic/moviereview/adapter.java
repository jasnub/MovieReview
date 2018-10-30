package com.example.muhic.moviereview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter <adapter.ExampleViewHolder>{
    private Context mContext;
    private ArrayList<item> mExampleList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public adapter(Context context, ArrayList<item> exampleList){
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        item currentItem = mExampleList.get(position);

        String imageUrl = currentItem.getmImageUrl();
        String title = currentItem.getmTitle();
        String overview = currentItem.getmOverview();
        String average = currentItem.getmAverage();


        holder.mTVOverview.setText(overview);
        holder.mTVTitle.setText(title);
        holder.mAverage.setText(average);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class ExampleViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView mTVTitle;
        public TextView mTVOverview;
        public TextView mAverage;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTVTitle = itemView.findViewById(R.id.text_title);
            mTVOverview = itemView.findViewById(R.id.text_overview);
            mAverage = itemView.findViewById(R.id.text_average);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
