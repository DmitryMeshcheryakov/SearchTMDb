package by.insight.searchtmdb.view.fragments.listing_movies;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import butterknife.ButterKnife;

import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.di.view.DaggerViewComponent;
import by.insight.searchtmdb.di.view.ViewComponent;
import by.insight.searchtmdb.di.view.ViewDynamicMoviesModule;
import by.insight.searchtmdb.presenter.base.BasePresenter;
import by.insight.searchtmdb.presenter.ListingMoviesPresenter;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.ActivityCallback;
import by.insight.searchtmdb.view.adapters.ListingMoviesAdapter;
import by.insight.searchtmdb.view.fragments.base.BaseFragment;

public class ListingMoviesFragment extends BaseFragment implements ListingMoviesView {

    @Bind(R.id.rv_listing_movies)
    RecyclerView mRvPopularMovies;


    @Inject
    protected ListingMoviesPresenter presenter;

    private ListingMoviesAdapter adapter;

    private ActivityCallback activityCallback;

    private ViewComponent viewComponent;

    private String mTypeData;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent
                    .builder()
                    .viewDynamicMoviesModule(new ViewDynamicMoviesModule(this))
                    .build();
        }
        viewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }


    public void setViewComponent(ViewComponent viewComponent) {
        this.viewComponent = viewComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.app_bar_main, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRvPopularMovies.setLayoutManager(llm);
        adapter = new ListingMoviesAdapter(new ArrayList<>(), presenter, getContext());
        mRvPopularMovies.setAdapter(adapter);
        presenter.setUpLoadMoreListener(mRvPopularMovies, llm);
        presenter.onCreateView(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mTypeData = bundle.getString(Constants.KEY_MOVIE);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter != null) {
            presenter.loadListingMovies(mTypeData);
        }
    }

    private void makeToast(String text) {
        Snackbar.make(mRvPopularMovies, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }


    @Override
    public void showListingMovies(List<ResultListingMovies> resultListingMovies) {
        adapter.addItemsAdapter(resultListingMovies);
    }


    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }


    @Override
    public void startDetailMovieFragment(ResultListingMovies repository) {
        activityCallback.startDetailMovieFragment(repository);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }



}
