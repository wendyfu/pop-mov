package com.wendy.fpt.popmov.presenter;

import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.domain.interactor.DefaultSubscriber;
import com.wendy.fpt.popmov.domain.interactor.GetPopularMoviesUseCase;
import com.wendy.fpt.popmov.domain.interactor.GetTopRatedMoviesUseCase;
import com.wendy.fpt.popmov.domain.interactor.UseCase;
import com.wendy.fpt.popmov.view.MainView;

import javax.inject.Inject;

public class MainPresenter extends AbstractPresenter<MainView> {

    private GetPopularMoviesUseCase getPopularMoviesUseCase;
    private GetTopRatedMoviesUseCase getTopRatedMoviesUseCase;

    @Inject
    public MainPresenter(GetPopularMoviesUseCase getPopularMoviesUseCase,
                         GetTopRatedMoviesUseCase getTopRatedMoviesUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
        this.getTopRatedMoviesUseCase = getTopRatedMoviesUseCase;
    }

    @Override
    protected UseCase[] useCases() {
        return new UseCase[]{
                getPopularMoviesUseCase, getTopRatedMoviesUseCase
        };
    }

    @Override
    void resume() {

    }

    @Override
    void pause() {

    }

    public void getPopularMovies() {
        getTopRatedMoviesUseCase.unsubscribe();
        getPopularMoviesUseCase.execute(new GetMoviesSubscriber(), null);
    }

    public void getTopRatedMovies() {
        getPopularMoviesUseCase.unsubscribe();
        getTopRatedMoviesUseCase.execute(new GetMoviesSubscriber(), null);
    }

    private final class GetMoviesSubscriber extends DefaultSubscriber<TMDBMovieDetailsResponse> {

        @Override
        public void onStart() {
            super.onStart();
            view.clearMovieList();
            view.setProgressBarVisibility(true);
            view.setErrorVisibility(false);
        }

        @Override
        public void onNext(TMDBMovieDetailsResponse tmdbMovieDetailsResponse) {
            super.onNext(tmdbMovieDetailsResponse);
            view.addMovie(tmdbMovieDetailsResponse);
            view.setProgressBarVisibility(false);
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            view.setErrorVisibility(true);
            view.setProgressBarVisibility(false);
        }
    }
}
