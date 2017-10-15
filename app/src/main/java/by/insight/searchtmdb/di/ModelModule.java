package by.insight.searchtmdb.di;


import javax.inject.Named;
import javax.inject.Singleton;

import by.insight.searchtmdb.Constants;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @Singleton
    @Named(Constants.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Constants.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

}
