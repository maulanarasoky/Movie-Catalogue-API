package com.example.moviecatalogueapi.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.example.moviecatalogueapi.Data.ListDetailsMovie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainViewModelDetailsMovie extends ViewModel {

    private static final String API_KEY = "fb0e5ff27afe26ed13f939e837260424";
    private MutableLiveData<ArrayList<ListDetailsMovie>> listData = new MutableLiveData<>();

    public void setData(String id, String language) {
        AsyncHttpClient client = new AsyncHttpClient();
        final ArrayList<ListDetailsMovie> data = new ArrayList<>();
        String url = "https://api.themoviedb.org/3/movie/" + id + "?api_key=" + API_KEY + "&language=" + language;
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    String result = new String(responseBody);
                    JSONObject responseObject = new JSONObject(result);
                    ListDetailsMovie listDetailsMovie = new ListDetailsMovie(responseObject);
                    data.add(listDetailsMovie);
                    listData.postValue(data);
                } catch (
                        Exception e) {
                    Log.d("Failed", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable
                    error) {
                Log.d("onFailure", error.getMessage());
            }
        });
    }

    public LiveData<ArrayList<ListDetailsMovie>> getData() {
        return listData;
    }
}
