package by.insight.searchtmdb.di;



import javax.inject.Singleton;

import by.insight.searchtmdb.model.base.Model;
import by.insight.searchtmdb.model.base.ModelImpl;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;


@Module

public class PresenterModule {

    @Provides
    @Singleton
    Model provideDataRepository() {
        return new ModelImpl();
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

}
