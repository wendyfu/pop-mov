package com.wendy.fpt.popmov.data.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel public class TMDBMovieDetailsResponse {

    @SerializedName("id") int id;
    @SerializedName("poster_path") String poster;
    @SerializedName("original_title") String title;
    @SerializedName("release_date") String releaseDate;
    @SerializedName("runtime") int duration;
    @SerializedName("vote_average") float rating;
    @SerializedName("overview") String overview;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    @Override public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof TMDBMovieDetailsResponse)) return false;
        return getId() == ((TMDBMovieDetailsResponse) obj).getId();
    }
}
