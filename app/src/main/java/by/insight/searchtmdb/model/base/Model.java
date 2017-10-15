package by.insight.searchtmdb.model.base;

import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.model.datail_movie_model.DetailMoviesDTO;
import by.insight.searchtmdb.model.listing_movies_model.ResultListingMoviesDTO;
import by.insight.searchtmdb.model.listing_tv_model.ListingTV_ModelDTO;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Dmitry on 08.10.2017.
 */

public interface Model {

    Observable<ListingMoviesModelDTO> getTopRatedMovies(
            String apiKey,
            int page,
            String language
    );


    Observable<DetailMoviesDTO> getMovieDetails(
            String movieId,
            String apiKey,
            String language
    );

    Observable<ListingMoviesModelDTO> getPopularMovies(
            String apiKey,
            int page,
            String language
    );



    Observable<ListingMoviesModelDTO> getNowPlayingMovies(
            String apiKey,
            int page,
            String language
    );


    Observable<ListingMoviesModelDTO> getUpcomingMovies(
            String apiKey,
            int page,
            String language
    );


    Observable<ListingMoviesModelDTO> searchMovies(
            String query,
            String apiKey,
            int page,
            String language
    );

    Observable<ListingTV_ModelDTO> getPopularTV(
            String apiKey,
            int page,
            String language
    );


    Observable<ListingTV_ModelDTO> getTopRatedTV(
            String apiKey,
            int page,
            String language
    );

    Observable<ListingTV_ModelDTO> searchTV(
            String query,
            String apiKey,
            int page,
            String language
    );



}
