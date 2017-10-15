package by.insight.searchtmdb.view.adapters;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.presenter.ListingMoviesPresenter;
import by.insight.searchtmdb.presenter.SearchMoviesPresenter;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.adapters.base.BaseAdapterMovies;

public class ListingMoviesAdapter extends BaseAdapterMovies<ResultListingMovies> {

    private ListingMoviesPresenter mListingMoviesPresenter;
    private SearchMoviesPresenter mSearchMoviesPresenter;
    private Context mContext;
    private final RequestOptions mRequestOptions;

    public ListingMoviesAdapter(List<ResultListingMovies> list, ListingMoviesPresenter presenter, Context context) {
        super(list);
        this.mContext = context;
        this.mListingMoviesPresenter = presenter;
        mRequestOptions = new RequestOptions().optionalCenterInside();
        mRequestOptions.placeholder(R.drawable.ic_cached_red_200);
    }

    public ListingMoviesAdapter(List<ResultListingMovies> list, SearchMoviesPresenter presenter, Context context) {
        super(list);
        this.mContext = context;
        this.mSearchMoviesPresenter = presenter;
        mRequestOptions = new RequestOptions().optionalCenterInside();
        mRequestOptions.placeholder(R.drawable.ic_cached_red_200);
    }


    @Override
    public void onBindViewHolder(BaseAdapterMovies.ViewHolder viewHolder, int i) {
        ResultListingMovies repo = list.get(i);
        viewHolder.mTitlePopularMovies.setText(repo.getTitle());
        viewHolder.mRatingPopularMovies.setText(String.valueOf(repo.getVoteAverage()));
        Glide.with(mContext)
                .load(Constants.IMAGE_URL + repo.getPosterPath())
                .apply(mRequestOptions)
                .into(viewHolder.mImagePopularMovies);
        viewHolder.mImagePopularMovies.setOnClickListener(v ->
                mListingMoviesPresenter.clickRepo(repo));
    }



    public void setAdapterList(List<ResultListingMovies> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItemsAdapter(List<ResultListingMovies> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
    }


}
