package com.example.moviecatalogueapi.Data;

import org.json.JSONObject;

public class ListDetailsTv {
    private String title, description, release_date, runtime, ratingValue, production_companies, genres, homepage, poster;
    private float ratingBar;

    public ListDetailsTv(JSONObject object) {
        try {
            this.title = object.getString("name");
            this.description = object.getString("overview");
            this.release_date = object.getJSONArray("seasons").getJSONObject(0).getString("air_date");
            this.runtime = object.getString("episode_run_time");
            this.ratingValue = object.getString("vote_average") + " / 10.0";
            this.production_companies = object.getJSONArray("production_companies").getJSONObject(0).getString("name");
            this.genres = object.getJSONArray("genres").getJSONObject(0).getString("name");
            this.homepage = object.getString("homepage");
            this.poster = ("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));
            this.ratingBar = object.getLong("vote_average");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(String ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(String production_companies) {
        this.production_companies = production_companies;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public float getRatingBar() {
        return ratingBar;
    }

    public void setRatingBar(float ratingBar) {
        this.ratingBar = ratingBar;
    }
}
