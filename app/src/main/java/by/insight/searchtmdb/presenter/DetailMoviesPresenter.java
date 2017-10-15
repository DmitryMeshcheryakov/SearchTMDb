package by.insight.searchtmdb.presenter;

import javax.inject.Inject;

import by.insight.searchtmdb.App;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.presenter.base.BasePresenter;
import by.insight.searchtmdb.presenter.mapper.RepoDetailMoviesMapper;
import by.insight.searchtmdb.presenter.vo.ResultDetailMovies;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.fragments.base.View;
import by.insight.searchtmdb.view.fragments.listing_movies.DetailMoviesView;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;


public class DetailMoviesPresenter extends BasePresenter {

    private static final String BUNDLE_DETAILS_MOVIES_KEY = "BUNDLE_KEY_DETAILS_MOVIES";

    @Inject
    protected RepoDetailMoviesMapper mRepoDetailMoviesMapper;

    private DetailMoviesView mDetailMoviesView;

    private ResultListingMovies mResultDetailMovies;


    @Inject
    public DetailMoviesPresenter() {
    }

    protected View getView() {
        return mDetailMoviesView;
    }

    public void loadData() {
        showLoadingState();
        Disposable disposable = model.getMovieDetails(String.valueOf(mResultDetailMovies.getId()), Constants.TOKEN, Constants.LANGUAGE_EN)
                .map(mRepoDetailMoviesMapper)
                .subscribeWith(new DisposableObserver<ResultDetailMovies>() {
                    @Override
                    public void onNext(@NonNull ResultDetailMovies resultDetailMovies) {
                        mDetailMoviesView.showImage(resultDetailMovies.getBackdropPath());
                        mDetailMoviesView.showOverview(resultDetailMovies.getOverview());
                        mDetailMoviesView.showRating(resultDetailMovies.getVoteAverage());
                        mDetailMoviesView.showName(resultDetailMovies.getOriginalTitle());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        showError(e);
                    }

                    @Override
                    public void onComplete() {
                        hideLoadingState();
                    }
                });
        addDisposable(disposable);
    }

    public void onCreate(DetailMoviesView view, ResultListingMovies resultListingMovies) {
        App.getComponent().inject(this);
        this.mDetailMoviesView = view;
        this.mResultDetailMovies = resultListingMovies;
    }
}
