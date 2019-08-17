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

import com.example.moviecatalogueapi.Adapter.ListAdapterTv;
import com.example.moviecatalogueapi.Data.ListData;
import com.example.moviecatalogueapi.ViewModel.MainViewModelTv;

import java.util.ArrayList;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {

    ProgressBar progressBar;
    ListAdapterTv listAdapterTv;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        progressBar = view.findViewById(R.id.progressBar);


        MainViewModelTv mainViewModelTv = ViewModelProviders.of(getActivity()).get(MainViewModelTv.class);
        if (Locale.getDefault().getDisplayLanguage().toString().equals("Indonesia")) {
            mainViewModelTv.setData("id");
        } else if (Locale.getDefault().getDisplayLanguage().toString().equals("English")) {
            mainViewModelTv.setData("en");
        }
        showLoading(true);
        mainViewModelTv.getData().observe(this, getTv);

        listAdapterTv = new ListAdapterTv();
        listAdapterTv.notifyDataSetChanged();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(listAdapterTv);
    }

    private Observer<ArrayList<ListData>> getTv = new Observer<ArrayList<ListData>>() {
        @Override
        public void onChanged(@Nullable ArrayList<ListData> listItems) {
            if (listItems != null) {
                listAdapterTv.setData(listItems);
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
