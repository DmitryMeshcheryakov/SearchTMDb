package by.insight.searchtmdb.di;



import by.insight.searchtmdb.presenter.DetailMoviesPresenter;
import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    @Provides
    DetailMoviesPresenter provideDetailMoviesPresenter() {
        return new DetailMoviesPresenter();
    }

}
