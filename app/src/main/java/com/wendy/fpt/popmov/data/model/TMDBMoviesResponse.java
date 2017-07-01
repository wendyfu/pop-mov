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

import java.util.List;

public class TMDBMoviesResponse {

    @SerializedName("page") private int page;
    @SerializedName("total_results") private int totalResults;
    @SerializedName("total_pages") private int totalPages;

    private List<MovieResponse> movies;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<MovieResponse> getMovies() {
        return movies;
    }

    public void setMovies(List<MovieResponse> movies) {
        this.movies = movies;
    }

    public static class MovieResponse {

        @SerializedName("id") private int movieId;
    }
}
