package by.insight.searchtmdb;

import android.app.Application;

import by.insight.searchtmdb.di.AppComponent;
import by.insight.searchtmdb.di.DaggerAppComponent;
import by.insight.searchtmdb.di.ModelModule;
import by.insight.searchtmdb.di.NetworkModule;
import by.insight.searchtmdb.di.PresenterModule;
import by.insight.searchtmdb.di.ViewModule;
import by.insight.searchtmdb.di.AppModule;


public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = buildComponent();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .modelModule(new ModelModule())
                .presenterModule(new PresenterModule())
                .viewModule(new ViewModule())
                .networkModule(new NetworkModule())
                .build();
    }


}
