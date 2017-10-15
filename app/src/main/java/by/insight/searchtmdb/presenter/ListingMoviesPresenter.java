package by.insight.searchtmdb.presenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;


import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import by.insight.searchtmdb.App;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.TypeData;
import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.presenter.base.BasePresenter;
import by.insight.searchtmdb.presenter.mapper.RepoListMapper;
import by.insight.searchtmdb.presenter.mapper.RepoObjectMapper;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.tools.RxSearch;
import by.insight.searchtmdb.view.fragments.listing_movies.ListingMoviesView;
import by.insight.searchtmdb.view.fragments.base.View;
import by.insight.searchtmdb.view.fragments.listing_movies.SearchMoviesView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;


public class ListingMoviesPresenter extends BasePresenter {

    private static final String BUNDLE_MOVIES_KEY = "BUNDLE_KEY";

    @Inject
    protected RepoListMapper repoListMapper;
    @Inject
    protected RepoObjectMapper mRepoObjectMapper;

    private ListingMoviesView mListingMoviesView;

    private List<ResultListingMovies> repoList;

    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private PublishProcessor<Integer> paginator = PublishProcessor.create();

    @Inject
    public ListingMoviesPresenter() {
    }

    public ListingMoviesPresenter(ListingMoviesView view) {
        App.getComponent().inject(this);
        this.mListingMoviesView = view;
    }


    protected View getView() {
        return mListingMoviesView;
    }


    public void loadListingMovies(String type) {
        showLoadingState();
        Disposable disposable = paginator
                .onBackpressureDrop()
                .concatMap(integer -> {
                    loading = true;
                    return loadData(type, integer);
                })
                .map(mRepoObjectMapper)
                .map(repoListMapper)
                .subscribe(resultListingMovies -> {
                    if (resultListingMovies != null && !resultListingMovies.isEmpty()) {
                        if (repoList != null) repoList.clear();
                        repoList = resultListingMovies;
                        mListingMoviesView.showListingMovies(resultListingMovies);
                        loading = false;
                        hideLoadingState();
                    } else {
                        mListingMoviesView.showEmptyList();
                    }
                }, this::handleError);

        addDisposable(disposable);
        paginator.onNext(pageNumber);
    }


    private void handleError(Throwable throwable) {
        showError(throwable);
    }

    public void setUpLoadMoreListener(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView,
                                   int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                totalItemCount = linearLayoutManager.getItemCount();
                lastVisibleItem = linearLayoutManager
                        .findLastVisibleItemPosition();
                if (!loading
                        && totalItemCount <= (lastVisibleItem + VISIBLE_THRESHOLD)) {
                    pageNumber++;
                    paginator.onNext(pageNumber);
                    loading = true;
                }
            }
        });
    }


    private Publisher<ListingMoviesModelDTO> loadData(String typeData, Integer pageNumber) {
        Observable<ListingMoviesModelDTO> observable = null;
        Publisher<ListingMoviesModelDTO> publisher = null;

        switch (typeData) {
            case TypeData.POPULAR_MOVIES: {
                observable = model.getPopularMovies(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }
            case TypeData.TOP_RATED_MOVIES: {
                observable = model.getTopRatedMovies(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }
            case TypeData.NOW_PLAYING_MOVIES: {
                observable = model.getNowPlayingMovies(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }
            case TypeData.UPCOMING_MOVIES: {
                observable = model.getUpcomingMovies(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }

        }
        publisher = observable.toFlowable(BackpressureStrategy.BUFFER);
        return publisher;

    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            try {
                repoList = (List<ResultListingMovies>) savedInstanceState.getSerializable(BUNDLE_MOVIES_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (isRepoListNotEmpty()) {
            mListingMoviesView.showListingMovies(repoList);
        }
    }


    private boolean isRepoListNotEmpty() {
        return (repoList != null && !repoList.isEmpty());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_MOVIES_KEY, new ArrayList<>(repoList));
        }
    }


    public void clickRepo(ResultListingMovies repository) {
        mListingMoviesView.startDetailMovieFragment(repository);
    }
}
