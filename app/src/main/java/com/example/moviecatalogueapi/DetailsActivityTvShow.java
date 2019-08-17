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

import com.example.moviecatalogueapi.Adapter.ListAdapterDetailsTv;
import com.example.moviecatalogueapi.Data.ListDetailsTv;
import com.example.moviecatalogueapi.ViewModel.MainViewModelDetailsTv;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class DetailsActivityTvShow extends AppCompatActivity {

    public static final String ID = "id";
    ProgressBar progressBar;
    ListAdapterDetailsTv listAdapterDetailsTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_tv_show);

        progressBar = findViewById(R.id.progressBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        MainViewModelDetailsTv mainViewModelDetailsTv = ViewModelProviders.of(this).get(MainViewModelDetailsTv.class);

        if (Locale.getDefault().getDisplayLanguage().toString().equals("Indonesia")) {
            mainViewModelDetailsTv.setData(getIntent().getStringExtra(ID), "id");
        } else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")) {
            mainViewModelDetailsTv.setData(getIntent().getStringExtra(ID), "en");
        }


        showLoading(true);
        mainViewModelDetailsTv.getData().observe(this, getTv);

        listAdapterDetailsTv = new ListAdapterDetailsTv();
        listAdapterDetailsTv.notifyDataSetChanged();

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapterDetailsTv);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private Observer<ArrayList<ListDetailsTv>> getTv = new Observer<ArrayList<ListDetailsTv>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ListDetailsTv> listItems) {
            if (listItems != null) {
                listAdapterDetailsTv.setData(listItems);
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
