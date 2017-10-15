package by.insight.searchtmdb.di.view;

import by.insight.searchtmdb.presenter.ListingTvPresenter;
import by.insight.searchtmdb.view.fragments.listing_tv.ListingTvView;
import dagger.Module;
import dagger.Provides;


@Module
public class ViewDynamicTvModule {

    private ListingTvView mListingMoviesView;

    public ViewDynamicTvModule(ListingTvView view) {
        this.mListingMoviesView = view;
    }

    @Provides
    ListingTvPresenter provideListingTvPresenter() {
        return new ListingTvPresenter(mListingMoviesView);
    }
}
