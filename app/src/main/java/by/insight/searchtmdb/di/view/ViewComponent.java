package by.insight.searchtmdb.di.view;



import javax.inject.Singleton;

import by.insight.searchtmdb.view.fragments.listing_movies.ListingMoviesFragment;
import by.insight.searchtmdb.view.fragments.listing_movies.SearchMoviesFragment;
import by.insight.searchtmdb.view.fragments.listing_tv.ListingTvFragment;
import dagger.Component;

@Singleton
@Component(modules = {ViewDynamicMoviesModule.class})
public interface ViewComponent {

    void inject(ListingMoviesFragment repoListFragment);
    void inject(SearchMoviesFragment searchMoviesFragment);


}
