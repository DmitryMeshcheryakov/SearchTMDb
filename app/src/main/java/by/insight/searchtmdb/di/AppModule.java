package by.insight.searchtmdb.di;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Dmitry on 09.10.2017.
 */
@Module
public class AppModule {
    private Application mApp;

    public AppModule(Application app) {
        this.mApp = app;
    }

    @Singleton
    @Provides
    Application provideApplication() {
        return mApp;
    }
}
