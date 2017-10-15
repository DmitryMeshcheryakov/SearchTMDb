package by.insight.searchtmdb.view.fragments.listing_tv;


import java.util.List;

import by.insight.searchtmdb.presenter.vo.ResultListingTv;
import by.insight.searchtmdb.view.fragments.base.View;

public interface ListingTvView extends View {

    void showListingTv(List<ResultListingTv> resultListingTvs);

    void showEmptyList();
}
