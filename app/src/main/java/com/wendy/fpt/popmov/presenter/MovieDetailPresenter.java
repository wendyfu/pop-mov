package com.wendy.fpt.popmov.presenter;

import android.net.Uri;

import com.wendy.fpt.popmov.data.model.TMDBMovieDetailsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieReviewsResponse;
import com.wendy.fpt.popmov.data.model.TMDBMovieVideosResponse;
import com.wendy.fpt.popmov.domain.DefaultSubscriber;
import com.wendy.fpt.popmov.domain.interactor.UseCase;
import com.wendy.fpt.popmov.domain.interactor.database.AddFavoriteMovieUseCase;
import com.wendy.fpt.popmov.domain.interactor.database.CheckFavoritedUseCase;
import com.wendy.fpt.popmov.domain.interactor.database.RemoveFavoriteUseCase;
import com.wendy.fpt.popmov.domain.interactor.tmdbservice.GetMovieReviewsUseCase;
import com.wendy.fpt.popmov.domain.interactor.tmdbservice.GetMovieVideosUseCase;
import com.wendy.fpt.popmov.view.MovieDetailView;

import javax.inject.Inject;

public class MovieDetailPresenter extends AbstractPresenter<MovieDetailView> {

    private GetMovieVideosUseCase getMovieVideosUseCase;
    private GetMovieReviewsUseCase getMovieReviewsUseCase;
    private AddFavoriteMovieUseCase addFavoriteMovieUseCase;
    private CheckFavoritedUseCase checkFavoritedUseCase;
    private RemoveFavoriteUseCase removeFavoriteUseCase;

    @Inject public MovieDetailPresenter(GetMovieVideosUseCase getMovieVideosUseCase,
        GetMovieReviewsUseCase getMovieReviewsUseCase,
        AddFavoriteMovieUseCase addFavoriteMovieUseCase,
        CheckFavoritedUseCase checkFavoritedUseCase, RemoveFavoriteUseCase removeFavoriteUseCase) {
        this.getMovieVideosUseCase = getMovieVideosUseCase;
        this.getMovieReviewsUseCase = getMovieReviewsUseCase;
        this.addFavoriteMovieUseCase = addFavoriteMovieUseCase;
        this.checkFavoritedUseCase = checkFavoritedUseCase;
        this.removeFavoriteUseCase = removeFavoriteUseCase;
    }

    @Override protected UseCase[] useCases() {
        return new UseCase[] {
            getMovieVideosUseCase, getMovieReviewsUseCase, addFavoriteMovieUseCase
        };
    }

    @Override void resume() {

    }

    @Override void pause() {

    }

    public void checkFavorited(int id) {
        checkFavoritedUseCase.execute(new DefaultSubscriber<Boolean>() {
            @Override public void onNext(Boolean isFavorited) {
                super.onNext(isFavorited);
                view.enableMarkAsFavorite(isFavorited);
            }
        }, id);
    }

    public void setupMovieVideos(int id) {
        getMovieVideosUseCase.execute(new MovieVideosSubscriber(), id);
    }

    public void setupMovieReviews(int id) {
        getMovieReviewsUseCase.execute(
            new DefaultSubscriber<TMDBMovieReviewsResponse.MovieReview>() {
                @Override public void onNext(TMDBMovieReviewsResponse.MovieReview movieReview) {
                    super.onNext(movieReview);
                    view.addReview(movieReview);
                }
            }, id);
    }

    public void addFavorite(TMDBMovieDetailsResponse movieDetail) {
        addFavoriteMovieUseCase.execute(new DefaultSubscriber<Uri>() {
            @Override public void onNext(Uri uri) {
                super.onNext(uri);
                if (uri != null) {
                    view.enableMarkAsFavorite(true);
                    view.showToastActionFavorite(true);
                }
            }
        }, movieDetail);
    }

    public void removeFavorite(int movieId) {
        removeFavoriteUseCase.execute(new DefaultSubscriber<Boolean>() {
            @Override public void onNext(Boolean isSuccess) {
                super.onNext(isSuccess);
                view.enableMarkAsFavorite(false);
                view.showToastActionFavorite(false);
            }
        }, movieId);
    }

    class MovieVideosSubscriber extends DefaultSubscriber<TMDBMovieVideosResponse.MovieVideo> {

        @Override public void onStart() {
            super.onStart();
            view.setProgressBarVisibility(true);
        }

        @Override public void onNext(TMDBMovieVideosResponse.MovieVideo movieVideo) {
            super.onNext(movieVideo);
            view.addVideo(movieVideo);
        }

        @Override public void onCompleted() {
            super.onCompleted();
            view.setProgressBarVisibility(false);
        }

        @Override public void onError(Throwable e) {
            super.onError(e);
            view.setProgressBarVisibility(false);
        }
    }
}
