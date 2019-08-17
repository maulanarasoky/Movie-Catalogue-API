package com.example.moviecatalogueapi.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueapi.Data.ListData;
import com.example.moviecatalogueapi.DetailsActivityMovie;
import com.example.moviecatalogueapi.R;

import java.util.ArrayList;

public class ListAdapterMovie extends RecyclerView.Adapter<ListAdapterMovie.ListViewHolder> {

    private ArrayList<ListData> listData = new ArrayList<>();

    public void setData(ArrayList<ListData> items) {
        listData.clear();
        listData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_list, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder listViewHolder, final int position) {
        listViewHolder.title.setText(listData.get(position).getTitle());
        if (listData.get(position).getDescription().equals("[]") || listData.get(position).getDescription().equals("null") || listData.get(position).getDescription().equals("")) {
            listViewHolder.description.setText(" - ");
        } else {
            listViewHolder.description.setText(listData.get(position).getDescription());
        }
        if (listData.get(position).getReleaseDate().equals("[]") || listData.get(position).getReleaseDate().equals("null") || listData.get(position).getReleaseDate().equals("")) {
            listViewHolder.releaseDate.setText(" - ");
        } else {
            listViewHolder.releaseDate.setText(listData.get(position).getReleaseDate());
        }
        if (listData.get(position).getRatings() > 0.0 && listData.get(position).getRatings() <= 1.0) {
            listViewHolder.ratings.setRating(0.5f);
        } else if (listData.get(position).getRatings() > 1.0 && listData.get(position).getRatings() <= 2.0) {
            listViewHolder.ratings.setRating(1.0f);
        } else if (listData.get(position).getRatings() > 2.0 && listData.get(position).getRatings() <= 3.0) {
            listViewHolder.ratings.setRating(1.5f);
        } else if (listData.get(position).getRatings() > 3.0 && listData.get(position).getRatings() <= 4.0) {
            listViewHolder.ratings.setRating(2.0f);
        } else if (listData.get(position).getRatings() > 4.0 && listData.get(position).getRatings() <= 5.0) {
            listViewHolder.ratings.setRating(2.5f);
        } else if (listData.get(position).getRatings() > 5.0 && listData.get(position).getRatings() <= 6.0) {
            listViewHolder.ratings.setRating(3.0f);
        } else if (listData.get(position).getRatings() > 6.0 && listData.get(position).getRatings() <= 7.0) {
            listViewHolder.ratings.setRating(3.5f);
        } else if (listData.get(position).getRatings() > 7.0 && listData.get(position).getRatings() <= 8.0) {
            listViewHolder.ratings.setRating(4.0f);
        } else if (listData.get(position).getRatings() > 8.0 && listData.get(position).getRatings() <= 9.0) {
            listViewHolder.ratings.setRating(4.5f);
        } else if (listData.get(position).getRatings() > 9.0 && listData.get(position).getRatings() <= 10.0) {
            listViewHolder.ratings.setRating(5.0f);
        } else {
            listViewHolder.ratings.setRating(0.0f);
        }
        Glide.with(listViewHolder.itemView.getContext())
                .load(listData.get(position).getPhoto())
                .apply(new RequestOptions().override(130, 220))
                .into(listViewHolder.imgPhoto);

        listViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(listViewHolder.itemView.getContext(), DetailsActivityMovie.class);
                intent.putExtra(DetailsActivityMovie.ID, listData.get(position).getId());
                listViewHolder.itemView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView title, description, releaseDate;
        RatingBar ratings;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            title = itemView.findViewById(R.id.tv_name);
            description = itemView.findViewById(R.id.tv_description);
            releaseDate = itemView.findViewById(R.id.release_date);
            ratings = itemView.findViewById(R.id.ratingBar);
        }
    }
}
