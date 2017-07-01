package com.wendy.fpt.popmov.presenter;

import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.domain.interactor.DefaultSubscriber;
import com.wendy.fpt.popmov.domain.interactor.GetPopularMoviesUseCase;
import com.wendy.fpt.popmov.domain.interactor.UseCase;
import com.wendy.fpt.popmov.view.MainView;

import javax.inject.Inject;

public class MainPresenter extends AbstractPresenter<MainView> {

    private GetPopularMoviesUseCase getPopularMoviesUseCase;

    @Inject
    public MainPresenter(GetPopularMoviesUseCase getPopularMoviesUseCase) {
        this.getPopularMoviesUseCase = getPopularMoviesUseCase;
    }

    @Override
    protected UseCase[] useCases() {
        return new UseCase[]{
                getPopularMoviesUseCase
        };
    }

    @Override
    void resume() {

    }

    @Override
    void pause() {

    }

    public void getPopularMovies() {
        getPopularMoviesUseCase.execute(new GetMoviesSubscriber(), null);
    }

    private final class GetMoviesSubscriber extends DefaultSubscriber<TMDBMovieDetailsResponse> {

        @Override
        public void onNext(TMDBMovieDetailsResponse tmdbMovieDetailsResponse) {
            super.onNext(tmdbMovieDetailsResponse);
            view.addMovie(tmdbMovieDetailsResponse);
        }
    }
}
