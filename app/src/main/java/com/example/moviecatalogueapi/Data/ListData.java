package com.example.moviecatalogueapi.Data;

import org.json.JSONObject;

public class ListData {
    private String id, photo, title, description, releaseDate;
    private float ratings;

    public ListData(JSONObject object, String title, String releaseDate) {
        try {
            this.id = object.getString("id");
            this.title = object.getString(title);
            this.description = object.getString("overview");
            this.releaseDate = object.getString(releaseDate);
            this.ratings = object.getLong("vote_average");
            this.photo = ("https://image.tmdb.org/t/p/w185" + object.getString("poster_path"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public float getRatings() {
        return ratings;
    }

    public void setRatings(float ratings) {
        this.ratings = ratings;
    }
}
