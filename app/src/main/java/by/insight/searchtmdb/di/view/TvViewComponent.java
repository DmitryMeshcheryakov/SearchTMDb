package by.insight.searchtmdb.di.view;

import javax.inject.Singleton;

import by.insight.searchtmdb.view.fragments.listing_tv.ListingTvFragment;
import dagger.Component;


@Singleton
@Component(modules = {ViewDynamicTvModule.class})
public interface TvViewComponent {

    void inject(ListingTvFragment listingTvFragment);
}
