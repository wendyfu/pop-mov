package com.wendy.fpt.popmov.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wendy.fpt.popmov.R;
import com.wendy.fpt.popmov.view.adapter.MoviePosterAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements MoviePosterAdapter.MoviePosterClickListener {

    @BindView(R.id.rv_mov_poster)
    RecyclerView rvMovPoster;

    private MoviePosterAdapter moviePosterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        setupRecyclerView();
        return view;
    }

    private void setupRecyclerView() {
        moviePosterAdapter = new MoviePosterAdapter(this);
        rvMovPoster.setAdapter(moviePosterAdapter);

        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        rvMovPoster.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onClick() {
        Intent movieDetailIntent = new Intent(getContext(), MovieDetailActivity.class);
        startActivity(movieDetailIntent);
    }
}
