package com.example.moviecatalogueapi.Data;

import com.example.moviecatalogueapi.R;

import org.json.JSONObject;

public class ListDetailsMovie {
    private String title, description, release_date, runtime, ratingValue, production_companies, production_countries, genres, homepage, poster;
    private float ratingBar;

    public ListDetailsMovie(JSONObject object) {
        try {
            this.title = object.getString("title");
            this.description = object.getString("overview");
            this.release_date = object.getString("release_date");
            this.runtime = object.getString("runtime");
            this.genres = object.getJSONArray("genres").getJSONObject(0).getString("name");
            this.production_companies = object.getJSONArray("production_companies").getJSONObject(0).getString("name");
            this.production_countries = object.getJSONArray("production_countries").getJSONObject(0).getString("name");
            this.ratingBar = object.getLong("vote_average");
            this.ratingValue = object.getString("vote_average") + " / 10.0";
            this.homepage = object.getString("homepage");
            this.poster = ("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(String production_companies) {
        this.production_companies = production_companies;
    }

    public String getProduction_countries() {
        return production_countries;
    }

    public void setProduction_countries(String production_countries) {
        this.production_countries = production_countries;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
