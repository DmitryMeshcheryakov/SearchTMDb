package by.insight.searchtmdb.di;


import javax.inject.Singleton;

import by.insight.searchtmdb.model.base.ModelImpl;
import by.insight.searchtmdb.presenter.DetailMoviesPresenter;
import by.insight.searchtmdb.presenter.ListingMoviesPresenter;
import by.insight.searchtmdb.presenter.ListingTvPresenter;
import by.insight.searchtmdb.presenter.SearchMoviesPresenter;
import by.insight.searchtmdb.presenter.base.BasePresenter;
import by.insight.searchtmdb.view.fragments.listing_movies.DetailMoviesFragment;
import dagger.Component;

@Singleton
@Component(modules = {ModelModule.class, PresenterModule.class, ViewModule.class, NetworkModule.class, AppModule.class})
public interface AppComponent {

    void inject(ModelImpl dataRepository);

    void inject(BasePresenter basePresenter);

    void inject(ListingMoviesPresenter repoListPresenter);

    void inject(SearchMoviesPresenter searchMoviesPresenter);

    void inject(ListingTvPresenter listingTvPresenter);

    void inject(DetailMoviesPresenter detailMoviesPresenter);

    void inject(DetailMoviesFragment detailMoviesFragment);

}
