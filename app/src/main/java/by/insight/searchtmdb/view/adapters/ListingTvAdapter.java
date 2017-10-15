package by.insight.searchtmdb.view.adapters;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.presenter.ListingTvPresenter;
import by.insight.searchtmdb.presenter.vo.ResultListingTv;
import by.insight.searchtmdb.view.adapters.base.BaseAdapterMovies;
import by.insight.searchtmdb.view.adapters.base.BaseAdapterTv;


public class ListingTvAdapter extends BaseAdapterTv<ResultListingTv> {

    private ListingTvPresenter mListingMoviesPresenter;
    private Context mContext;
    private final RequestOptions mRequestOptions;

    public ListingTvAdapter(List<ResultListingTv> list, ListingTvPresenter presenter, Context context) {
        super(list);
        this.mContext = context;
        this.mListingMoviesPresenter = presenter;
        mRequestOptions = new RequestOptions().optionalCenterInside();
        mRequestOptions.placeholder(R.drawable.ic_cached_red_200);
    }

    @Override
    public void onBindViewHolder(BaseAdapterTv.ViewHolder holder, int position) {
        ResultListingTv repo = list.get(position);
        holder.mNameTv.setText(repo.getName());
        holder.mRatingTv.setText(String.valueOf(repo.getVoteAverage()));
        Glide.with(mContext)
                .load(Constants.IMAGE_URL + repo.getPosterPath())
                .apply(mRequestOptions)
                .into(holder.mImageTv);
//        viewHolder.text.setOnClickListener(v ->
//                mListingMoviesPresenter.clickRepo(repo));
    }


    public void setRepoList(List<ResultListingTv> list) {
        this.list.clear();
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItems(List<ResultListingTv> items) {
        this.list.addAll(items);
        notifyDataSetChanged();
    }


}
