package com.wendy.fpt.popmov.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wendy.fpt.popmov.PopMovApplication;
import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.presenter.MainPresenter;
import com.wendy.fpt.popmov.view.MainView;
import com.wendy.fpt.popmov.view.adapter.MoviePosterAdapter;

import org.parceler.Parcels;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MainView, MoviePosterAdapter.MoviePosterClickListener {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.text_error)
    TextView textError;
    @BindView(R.id.rv_mov_poster)
    RecyclerView rvMovPoster;

    @Inject
    MainPresenter presenter;

    private MoviePosterAdapter moviePosterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);
        ((PopMovApplication) getActivity().getApplicationContext()).getAppComponent().inject(this);

        setupRecyclerView();
        presenter.setView(this);

        presenter.getPopularMovies();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_movie_popular:
                presenter.getPopularMovies();
                return true;
            case R.id.action_movie_top_rated:
                presenter.getTopRatedMovies();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupRecyclerView() {
        moviePosterAdapter = new MoviePosterAdapter(this);
        rvMovPoster.setAdapter(moviePosterAdapter);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvMovPoster.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onClick(TMDBMovieDetailsResponse movie) {
        Intent movieDetailIntent = new Intent(getContext(), MovieDetailActivity.class);
        movieDetailIntent.putExtra(Intent.EXTRA_INTENT, Parcels.wrap(movie));
        startActivity(movieDetailIntent);
    }

    @Override
    public void addMovie(TMDBMovieDetailsResponse movie) {
        moviePosterAdapter.addItem(movie);
    }

    @Override
    public void clearMovieList() {
        moviePosterAdapter.clear();
    }

    @Override
    public void setErrorVisibility(boolean isVisible) {
        textError.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressBarVisibility(boolean isVisible) {
        progressBar.setVisibility(isVisible ? View.VISIBLE : View.GONE);
    }
}
