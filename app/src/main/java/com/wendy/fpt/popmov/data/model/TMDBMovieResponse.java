package com.wendy.fpt.popmov.data.model;

//{
//        "page": 1,
//        "total_results": 19639,
//        "total_pages": 982,
//        "results": [
//        {
//        "vote_count": 1877,
//        "id": 297762,
//        "video": false,
//        "vote_average": 7,
//        "title": "Wonder Woman",
//        "popularity": 137.495481,
//        "poster_path": "/gfJGlDaHuWimErCr5Ql0I8x9QSy.jpg",
//        "original_language": "en",
//        "original_title": "Wonder Woman",
//        "genre_ids": [
//        28,
//        12,
//        14,
//        878
//        ],
//        "backdrop_path": "/hA5oCgvgCxj5MEWcLpjXXTwEANF.jpg",
//        "adult": false,
//        "overview": "An Amazon princess comes to the world of Man to become the greatest of the female superheroes.",
//        "release_date": "2017-05-30"
//        },


import com.google.gson.annotations.SerializedName;

public class TMDBMovieResponse {

    @SerializedName("page") private int page;
    @SerializedName("total_results") private int totalResults;
    @SerializedName("total_pages") private int totalPages;


    static class MovieResponse {

    }
}
