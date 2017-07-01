package com.wendy.fpt.popmov.data.model;

import com.google.gson.annotations.SerializedName;

public class TMDBMovieDetails {

    @SerializedName("poster_path") private String poster;
    @SerializedName("title") private String title;
    @SerializedName("release_date") private String releaseDate;
    @SerializedName("runtime") private int duration;
    @SerializedName("vote_average") private int rating;
    @SerializedName("overview") private String overview;
}
