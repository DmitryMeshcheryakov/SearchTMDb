package by.insight.searchtmdb.di.view;


import by.insight.searchtmdb.presenter.ListingMoviesPresenter;
import by.insight.searchtmdb.presenter.SearchMoviesPresenter;
import by.insight.searchtmdb.view.fragments.listing_movies.ListingMoviesView;
import by.insight.searchtmdb.view.fragments.listing_movies.SearchMoviesView;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewDynamicMoviesModule {

    private ListingMoviesView mListingMoviesView;
    private SearchMoviesView mSearchMoviesView;


    public ViewDynamicMoviesModule(ListingMoviesView view) {
        this.mListingMoviesView = view;
    }

    public ViewDynamicMoviesModule(SearchMoviesView searchMoviesView) {
        this.mSearchMoviesView = searchMoviesView;
    }

    @Provides
    ListingMoviesPresenter provideListingMoviesPresenter() {
        return new ListingMoviesPresenter(mListingMoviesView);
    }

    @Provides
    SearchMoviesPresenter provideSearchListingMoviesPresenter() {
        return new SearchMoviesPresenter(mSearchMoviesView);
    }

}
