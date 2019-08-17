package com.example.moviecatalogueapi.Adapter;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.moviecatalogueapi.Data.ListDetailsMovie;
import com.example.moviecatalogueapi.R;

import java.util.ArrayList;

public class ListAdapterDetailsMovie extends RecyclerView.Adapter<ListAdapterDetailsMovie.ListViewHolder> {

    private ArrayList<ListDetailsMovie> listData = new ArrayList<>();

    public void setData(ArrayList<ListDetailsMovie> items) {
        listData.clear();
        listData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row_details_movie, viewGroup, false);
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
        if (listData.get(position).getRelease_date().equals("[]") || listData.get(position).getRelease_date().equals("null") || listData.get(position).getRelease_date().equals("")) {
            listViewHolder.releaseDate.setText(" - ");
        } else {
            listViewHolder.releaseDate.setText(listData.get(position).getRelease_date());
        }

        if (listData.get(position).getRuntime().equals("[]") || listData.get(position).getRuntime().equals("null") || listData.get(position).getRuntime().equals("")) {
            listViewHolder.runtime.setText(" - ");
        } else {
            listViewHolder.runtime.setText(listData.get(position).getRuntime());
        }

        listViewHolder.ratingValue.setText(listData.get(position).getRatingValue());

        if (listData.get(position).getProduction_companies().equals("[]") || listData.get(position).getProduction_companies().equals("null") || listData.get(position).getProduction_companies().equals("")) {
            listViewHolder.productionCompanies.setText(" - ");
        } else {
            listViewHolder.productionCompanies.setText(listData.get(position).getProduction_companies());
        }

        if (listData.get(position).getGenres().equals("[]") || listData.get(position).getGenres().equals("null") || listData.get(position).getGenres().equals("")) {
            listViewHolder.genres.setText(" - ");
        } else {
            listViewHolder.genres.setText(listData.get(position).getGenres());
        }

        if (listData.get(position).getProduction_countries().equals("[]") || listData.get(position).getProduction_countries().equals("null") || listData.get(position).getProduction_countries().equals("")) {
            listViewHolder.productionCountries.setText(" - ");
        } else {
            listViewHolder.productionCountries.setText(listData.get(position).getProduction_countries());
        }

        if (listData.get(position).getRatingBar() > 0.0 && listData.get(position).getRatingBar() <= 1.0) {
            listViewHolder.ratingBar.setRating(0.5f);
        } else if (listData.get(position).getRatingBar() > 1.0 && listData.get(position).getRatingBar() <= 2.0) {
            listViewHolder.ratingBar.setRating(1.0f);
        } else if (listData.get(position).getRatingBar() > 2.0 && listData.get(position).getRatingBar() <= 3.0) {
            listViewHolder.ratingBar.setRating(1.5f);
        } else if (listData.get(position).getRatingBar() > 3.0 && listData.get(position).getRatingBar() <= 4.0) {
            listViewHolder.ratingBar.setRating(2.0f);
        } else if (listData.get(position).getRatingBar() > 4.0 && listData.get(position).getRatingBar() <= 5.0) {
            listViewHolder.ratingBar.setRating(2.5f);
        } else if (listData.get(position).getRatingBar() > 5.0 && listData.get(position).getRatingBar() <= 6.0) {
            listViewHolder.ratingBar.setRating(3.0f);
        } else if (listData.get(position).getRatingBar() > 6.0 && listData.get(position).getRatingBar() <= 7.0) {
            listViewHolder.ratingBar.setRating(3.5f);
        } else if (listData.get(position).getRatingBar() > 7.0 && listData.get(position).getRatingBar() <= 8.0) {
            listViewHolder.ratingBar.setRating(4.0f);
        } else if (listData.get(position).getRatingBar() > 8.0 && listData.get(position).getRatingBar() <= 9.0) {
            listViewHolder.ratingBar.setRating(4.5f);
        } else if (listData.get(position).getRatingBar() > 9.0 && listData.get(position).getRatingBar() <= 10.0) {
            listViewHolder.ratingBar.setRating(5.0f);
        } else {
            listViewHolder.ratingBar.setRating(0.0f);
        }

        Glide.with(listViewHolder.itemView.getContext())
                .load(listData.get(position).getPoster())
                .apply(new RequestOptions().override(160, 220))
                .into(listViewHolder.poster);

        listViewHolder.watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri;
                if (listData.get(position).getHomepage().equals("null") || listData.get(position).getHomepage().equals("")) {
                    uri = Uri.parse("http://mercusuar.uzone.id/");
                } else {
                    uri = Uri.parse(listData.get(position).getHomepage());
                }
                Intent watch = new Intent(Intent.ACTION_VIEW, uri);
                listViewHolder.itemView.getContext().startActivity(watch);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {

        ImageView poster;
        TextView title, description, releaseDate, ratingValue, genres, runtime, productionCompanies, productionCountries;
        RatingBar ratingBar;
        Button watch;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            poster = itemView.findViewById(R.id.img_item_photo);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            releaseDate = itemView.findViewById(R.id.release_date);
            genres = itemView.findViewById(R.id.genre);
            runtime = itemView.findViewById(R.id.runtime);
            productionCompanies = itemView.findViewById(R.id.production_companies);
            productionCountries = itemView.findViewById(R.id.production_countries);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            ratingValue = itemView.findViewById(R.id.ratingValue);
            watch = itemView.findViewById(R.id.watch);
        }
    }
}
