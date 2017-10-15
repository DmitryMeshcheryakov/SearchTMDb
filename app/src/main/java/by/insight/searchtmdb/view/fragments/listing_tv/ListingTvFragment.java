package by.insight.searchtmdb.view.fragments.listing_tv;

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
import by.insight.searchtmdb.di.view.DaggerTvViewComponent;
import by.insight.searchtmdb.di.view.DaggerViewComponent;
import by.insight.searchtmdb.di.view.TvViewComponent;
import by.insight.searchtmdb.di.view.ViewComponent;
import by.insight.searchtmdb.di.view.ViewDynamicTvModule;
import by.insight.searchtmdb.presenter.ListingTvPresenter;
import by.insight.searchtmdb.presenter.base.Presenter;
import by.insight.searchtmdb.presenter.vo.ResultListingTv;
import by.insight.searchtmdb.view.ActivityCallback;
import by.insight.searchtmdb.view.adapters.ListingTvAdapter;
import by.insight.searchtmdb.view.fragments.base.BaseFragment;


public class ListingTvFragment extends BaseFragment implements ListingTvView {


    @Bind(R.id.rv_listing_tv)
    RecyclerView mRecyclerView;

    @Inject
    protected ListingTvPresenter presenter;

    private ListingTvAdapter adapter;

    private ActivityCallback activityCallback;

    private TvViewComponent mTvViewComponent;

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
        if (mTvViewComponent == null) {
            mTvViewComponent = DaggerTvViewComponent
                    .builder()
                    .viewDynamicTvModule(new ViewDynamicTvModule(this))
                    .build();
        }
        mTvViewComponent.inject(this);
        super.onCreate(savedInstanceState);
    }

    public void setViewComponent(TvViewComponent tvViewComponent) {
        this.mTvViewComponent = tvViewComponent;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listing_tv_fragment, container, false);
        ButterKnife.bind(this, view);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(llm);
        adapter = new ListingTvAdapter(new ArrayList<>(), presenter, getContext());
        mRecyclerView.setAdapter(adapter);
        presenter.setUpLoadMoreListener(mRecyclerView, llm);
        presenter.onCreateView(savedInstanceState);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            mTypeData = bundle.getString(Constants.KEY_TV);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (presenter != null) {
            presenter.loadListingTv(mTypeData);
        }
    }

    private void makeToast(String text) {
        Snackbar.make(mRecyclerView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }


    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void showListingTv(List<ResultListingTv> resultListingTvs) {
        adapter.addItems(resultListingTvs);
    }

    @Override
    public void showEmptyList() {
        makeToast(getActivity().getString(R.string.empty_list));
    }

    @Override
    protected Presenter getPresenter() {
        return presenter;
    }
}
