package by.insight.searchtmdb.view.fragments.listing_movies;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import by.insight.searchtmdb.App;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.custom_view.CustomTextView;
import by.insight.searchtmdb.presenter.DetailMoviesPresenter;
import by.insight.searchtmdb.presenter.base.Presenter;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.fragments.base.BaseFragment;

import static by.insight.searchtmdb.tools.InitUtil.initToolbar;


public class DetailMoviesFragment extends BaseFragment implements DetailMoviesView {

    private static final String BUNDLE_REPO_KEY = "BUNDLE_REPO_KEY";

    @Bind(R.id.detail_image_movies)
    ImageView mDetailImageMovies;
    @Bind(R.id.detail_event_toolbar)
    Toolbar mDetailEventToolbar;
    @Bind(R.id.detail_movies_appbarr)
    AppBarLayout mDetailEventAppbar;
    @Bind(R.id.rating_bar_detail_movies)
    RatingBar mRatingBarDetailMovies;
    @Bind(R.id.title_detail_movies)
    CustomTextView mTitleDetailMovies;
    @Bind(R.id.overview_detail_movies)
    CustomTextView mOverviewDetailMovies;
    @Bind(R.id.detail_movies_collapsing)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    RequestOptions mRequestOptions;

    @Inject
    DetailMoviesPresenter mDetailMoviesPresenter;

    public static DetailMoviesFragment newInstance(ResultListingMovies resultListingMovies) {
        DetailMoviesFragment myFragment = new DetailMoviesFragment();
        Bundle args = new Bundle();
        args.putSerializable(BUNDLE_REPO_KEY, resultListingMovies);
        myFragment.setArguments(args);
        return myFragment;
    }

    private ResultListingMovies getResultListingMoviesVO() {
        return (ResultListingMovies) getArguments().getSerializable(BUNDLE_REPO_KEY);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        App.getComponent().inject(this);
        super.onCreate(savedInstanceState);
        mDetailMoviesPresenter.onCreate(this, getResultListingMoviesVO());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_movies_fragment, container, false);
        ButterKnife.bind(this, view);
        initToolbar(mDetailEventToolbar, getActivity());
        mCollapsingToolbarLayout.setTitle(" ");
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRequestOptions = new RequestOptions().optionalCenterInside();
        if (mDetailMoviesPresenter != null) {
            mDetailMoviesPresenter.loadData();
        }
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    protected Presenter getPresenter() {
        return mDetailMoviesPresenter;
    }


    @Override
    public void showImage(String s) {
        Glide.with(getContext())
                .load(Constants.IMAGE_URL + s)
                .apply(mRequestOptions)
                .into(mDetailImageMovies);
    }

    @Override
    public void showRating(Double d) {
        mRatingBarDetailMovies.setRating(Float.valueOf(String.valueOf(d)));
    }

    @Override
    public void showOverview(String e) {
        mOverviewDetailMovies.setText(e);
    }

    @Override
    public void showName(String q) {
        mTitleDetailMovies.setText(q);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    private void makeToast(String text) {
        Snackbar.make(getView(), text, Snackbar.LENGTH_LONG).show();
    }



}
