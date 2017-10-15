package by.insight.searchtmdb.presenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.reactivestreams.Publisher;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.App;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.TypeData;
import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.model.listing_tv_model.ListingTV_ModelDTO;
import by.insight.searchtmdb.presenter.base.BasePresenter;
import by.insight.searchtmdb.presenter.mapper.RepoTvListMapper;
import by.insight.searchtmdb.presenter.mapper.RepoTvObjectMapper;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.presenter.vo.ResultListingTv;
import by.insight.searchtmdb.view.fragments.base.View;
import by.insight.searchtmdb.view.fragments.listing_tv.ListingTvView;
import io.reactivex.BackpressureStrategy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.PublishProcessor;


public class ListingTvPresenter extends BasePresenter {

    private static final String BUNDLE_TV_KEY = "BUNDLE_KEY";

    @Inject
    protected RepoTvListMapper mRepoTvListMapper;
    @Inject
    protected RepoTvObjectMapper mRepoTvObjectMapper;

    private ListingTvView mListingTvView;

    private List<ResultListingTv> mResultListingTvs;

    private boolean loading = false;
    private int pageNumber = 1;
    private final int VISIBLE_THRESHOLD = 1;
    private int lastVisibleItem, totalItemCount;
    private PublishProcessor<Integer> paginator = PublishProcessor.create();

    @Inject
    public ListingTvPresenter() {
    }

    public ListingTvPresenter(ListingTvView view) {
        App.getComponent().inject(this);
        this.mListingTvView = view;
    }


    protected View getView() {
        return mListingTvView;
    }


    public void loadListingTv(String type) {
        showLoadingState();
        Disposable disposable = paginator
                .onBackpressureDrop()
                .concatMap(integer -> {
                    loading = true;
                    return loadData(type, integer);
                })
                .map(mRepoTvObjectMapper)
                .map(mRepoTvListMapper)
                .subscribe(resultListingTv -> {
                    if (resultListingTv != null && !resultListingTv.isEmpty()) {
                        if (mResultListingTvs != null) mResultListingTvs.clear();
                        mResultListingTvs = resultListingTv;
                        mListingTvView.showListingTv(resultListingTv);
                        loading = false;
                        hideLoadingState();
                    } else {
                        mListingTvView.showEmptyList();
                    }
                }, this::handleError);

        addDisposable(disposable);
        paginator.onNext(pageNumber);
    }


    private void handleError(Throwable throwable) {
        Log.e("TV_PRESENTER", throwable.getMessage());
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


    private Publisher<ListingTV_ModelDTO> loadData(String typeData, Integer pageNumber) {
        Observable<ListingTV_ModelDTO> observable = null;
        Publisher<ListingTV_ModelDTO> publisher = null;

        switch (typeData) {
            case TypeData.POPULAR_TV: {
                observable = model.getPopularTV(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }
            case TypeData.TOP_RATED_TV: {
                observable = model.getTopRatedTV(Constants.TOKEN, pageNumber, Constants.LANGUAGE_EN);
                break;
            }
        }
        publisher = observable.toFlowable(BackpressureStrategy.BUFFER);
        return publisher;

    }

    public void onCreateView(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            try {
                mResultListingTvs = (List<ResultListingTv>) savedInstanceState.getSerializable(BUNDLE_TV_KEY);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        if (isRepoListNotEmpty()) {
            mListingTvView.showListingTv(mResultListingTvs);
        }
    }


    private boolean isRepoListNotEmpty() {
        return (mResultListingTvs != null && !mResultListingTvs.isEmpty());
    }

    public void onSaveInstanceState(Bundle outState) {
        if (isRepoListNotEmpty()) {
            outState.putSerializable(BUNDLE_TV_KEY, new ArrayList<>(mResultListingTvs));
        }
    }

    public void clickRepo(ResultListingTv repository) {
//        mListingTvView.startDetailMovieFragment(repository);
    }
}
