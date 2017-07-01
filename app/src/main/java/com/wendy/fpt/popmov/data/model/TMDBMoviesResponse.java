package com.wendy.fpt.popmov.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TMDBMoviesResponse {

    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int totalResults;
    @SerializedName("total_pages")
    private int totalPages;

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

        @SerializedName("id")
        private int movieId;

        public int getMovieId() {
            return movieId;
        }
    }

    @Override
    public String toString() {
        return "page=" + page + " total_results=" + totalResults + " total_pages= " + totalPages
                + movies.toString();
    }
}
