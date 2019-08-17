package com.example.moviecatalogueapi.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.moviecatalogueapi.Data.ListData;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModelUpComing extends ViewModel {

    private static final String API_KEY = "fb0e5ff27afe26ed13f939e837260424";
    private MutableLiveData<ArrayList<ListData>> listData = new MutableLiveData<>();

    public void setData(String language) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ListData> data = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=" + API_KEY + "&language=" + language + "&page=3";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    JSONArray list = responseObject.getJSONArray("results");
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject allData = list.getJSONObject(i);
                        ListData dataAPI = new ListData(allData, "title", "release_date");
                        data.add(dataAPI);
                    }
                    listData.postValue(data);
                } catch (Exception e) {
                    Log.d("Failed", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<ListData>> getData() {
        return listData;
    }

}
