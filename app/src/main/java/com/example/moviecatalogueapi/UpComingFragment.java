package com.example.moviecatalogueapi;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviecatalogueapi.Adapter.ListAdapterMovie;
import com.example.moviecatalogueapi.Data.ListData;
import com.example.moviecatalogueapi.ViewModel.MainViewModelUpComing;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {

    ProgressBar progressBar;
    ListAdapterMovie listAdapterMovie;

    public UpComingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);


        MainViewModelUpComing mainViewModelUpComing = ViewModelProviders.of(getActivity()).get(MainViewModelUpComing.class);
        if (Locale.getDefault().getDisplayLanguage().toString().equals("Indonesia")) {
            mainViewModelUpComing.setData("id");
        } else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")) {
            mainViewModelUpComing.setData("en");
        }
        showLoading(true);
        mainViewModelUpComing.getData().observe(this, getMovie);

        listAdapterMovie = new ListAdapterMovie();
        listAdapterMovie.notifyDataSetChanged();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapterMovie);

    }

    private Observer<ArrayList<ListData>> getMovie = new Observer<ArrayList<ListData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ListData> listItems) {
            if (listItems != null) {
                listAdapterMovie.setData(listItems);
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
