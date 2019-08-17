package com.example.moviecatalogueapi;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.moviecatalogueapi.Adapter.ListAdapterDetailsMovie;
import com.example.moviecatalogueapi.Data.ListDetailsMovie;
import com.example.moviecatalogueapi.ViewModel.MainViewModelDetailsMovie;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class DetailsActivityMovie extends AppCompatActivity {

    public static final String ID = "id";
    ProgressBar progressBar;
    ListAdapterDetailsMovie listAdapterDetailsMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        progressBar = findViewById(R.id.progressBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        MainViewModelDetailsMovie mainViewModelDetailsMovie = ViewModelProviders.of(this).get(MainViewModelDetailsMovie.class);
        if (Locale.getDefault().getDisplayLanguage().toString().equals("Indonesia")) {
            mainViewModelDetailsMovie.setData(getIntent().getStringExtra(ID), "id");
        } else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")) {
            mainViewModelDetailsMovie.setData(getIntent().getStringExtra(ID), "en");
        }

        showLoading(true);
        mainViewModelDetailsMovie.getData().observe(this, getMovie);

        listAdapterDetailsMovie = new ListAdapterDetailsMovie();
        listAdapterDetailsMovie.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapterDetailsMovie);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Observer<ArrayList<ListDetailsMovie>> getMovie = new Observer<ArrayList<ListDetailsMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ListDetailsMovie> listItems) {
            if (listItems != null) {
                listAdapterDetailsMovie.setData(listItems);
                showLoading(false);
            }
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }
}
