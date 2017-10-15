package by.insight.searchtmdb.view.fragments.listing_movies;

import android.animation.LayoutTransition;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.di.view.DaggerViewComponent;
import by.insight.searchtmdb.di.view.ViewComponent;
import by.insight.searchtmdb.di.view.ViewDynamicMoviesModule;
import by.insight.searchtmdb.presenter.SearchMoviesPresenter;
import by.insight.searchtmdb.presenter.base.Presenter;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.ActivityCallback;
import by.insight.searchtmdb.view.adapters.ListingMoviesAdapter;
import by.insight.searchtmdb.view.fragments.base.BaseFragment;



public class SearchMoviesFragment extends BaseFragment implements SearchMoviesView {

    @Bind(R.id.rv_search_movies)
    RecyclerView mRecyclerView;


    @Inject
    protected SearchMoviesPresenter presenter;

    private ListingMoviesAdapter adapter;

    private ActivityCallback activityCallback;

    private ViewComponent viewComponent;


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
        View view = inflater.inflate(R.layout.search_movies_fragment, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        adapter = new ListingMoviesAdapter(new ArrayList<>(), presenter, getContext());
        mRecyclerView.setAdapter(adapter);
        presenter.setUpLoadMoreListener(mRecyclerView, llm);
        presenter.onCreateView(savedInstanceState);
        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(mRecyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showSearchListingMovies(List<ResultListingMovies> resultListingMovies) {
        adapter.setAdapterList(resultListingMovies);
    }

    @Override
    public void showAddSearch(List<ResultListingMovies> resultListingMovies) {
        adapter.addItemsAdapter(resultListingMovies);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main, menu);
        try {
            SearchManager searchManager =
                    (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView =
                    (SearchView) menu.findItem(R.id.action_search).getActionView();
            searchView.setSearchableInfo(
                    searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint("Search...");
            searchView.setLayoutTransition(new LayoutTransition());
            searchView.animate().setDuration(300);
            presenter.loadSearchMovies(searchView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
