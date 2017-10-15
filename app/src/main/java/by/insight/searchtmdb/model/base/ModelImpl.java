package by.insight.searchtmdb.model.base;

import javax.inject.Inject;
import javax.inject.Named;

import by.insight.searchtmdb.App;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.model.api.ApiInterface;
import by.insight.searchtmdb.model.datail_movie_model.DetailMoviesDTO;
import by.insight.searchtmdb.model.listing_movies_model.ResultListingMoviesDTO;
import by.insight.searchtmdb.model.listing_tv_model.ListingTV_ModelDTO;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;


public class ModelImpl implements Model {

    private final ObservableTransformer schedulersTransformer;

    @Inject
    protected ApiInterface apiInterface;

    @Inject
    @Named(Constants.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Constants.IO_THREAD)
    Scheduler ioThread;

    public ModelImpl() {
        App.getComponent().inject(this);
        schedulersTransformer = o -> (o)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .unsubscribeOn(ioThread);
    }

    @SuppressWarnings("unchecked")
    private <T> ObservableTransformer<T, T> applySchedulers() {
        return (ObservableTransformer<T, T>) schedulersTransformer;
    }


    //MOVIES

    @Override
    public Observable<ListingMoviesModelDTO> getTopRatedMovies(String apiKey, int page, String language) {
        return apiInterface.getTopRatedMovies(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<DetailMoviesDTO> getMovieDetails(String movieId, String apiKey, String language) {
        return apiInterface.getMovieDetails(movieId, apiKey, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingMoviesModelDTO> getPopularMovies(String apiKey, int page, String language) {
        return apiInterface.getPopularMovies(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingMoviesModelDTO> getNowPlayingMovies(String apiKey, int page, String language) {
        return apiInterface.getNowPlayingMovies(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingMoviesModelDTO> getUpcomingMovies(String apiKey, int page, String language) {
        return apiInterface.getUpcomingMovies(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingMoviesModelDTO> searchMovies(String query, String apiKey, int page, String language) {
        return apiInterface.searchMovies(query, apiKey, page, language)
                .compose(applySchedulers());
    }



    //TV

    @Override
    public Observable<ListingTV_ModelDTO> getPopularTV(String apiKey, int page, String language) {
        return apiInterface.getPopularTV(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingTV_ModelDTO> getTopRatedTV(String apiKey, int page, String language) {
        return apiInterface.getTopRatedTV(apiKey, page, language)
                .compose(applySchedulers());
    }

    @Override
    public Observable<ListingTV_ModelDTO> searchTV(String query, String apiKey, int page, String language) {
        return apiInterface.searchTv(query, apiKey, page, language)
                .compose(applySchedulers());
    }


}
