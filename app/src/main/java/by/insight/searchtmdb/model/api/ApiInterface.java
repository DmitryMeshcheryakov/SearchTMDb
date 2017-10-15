package by.insight.searchtmdb.model.api;


import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.model.datail_movie_model.DetailMoviesDTO;
import by.insight.searchtmdb.model.listing_tv_model.ListingTV_ModelDTO;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiInterface {


    @GET(Constants.TOP_RATED)
    Observable<ListingMoviesModelDTO> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET(Constants.MOVIE + "{id}")
    Observable<DetailMoviesDTO> getMovieDetails(
            @Path("id") String movieId,
            @Query("api_key") String apiKey,
            @Query("language") String language
    );

    @GET(Constants.POPULAR)
    Observable<ListingMoviesModelDTO> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );


    @GET(Constants.NOW_PLAYING)
    Observable<ListingMoviesModelDTO> getNowPlayingMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET(Constants.SEARCH_MOVIE)
    Observable<ListingMoviesModelDTO> searchMovies(
            @Query("query") String query,
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );



    @GET(Constants.UPCOMING)
    Observable<ListingMoviesModelDTO> getUpcomingMovies(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );




    @GET(Constants.TV_POPULAR)
    Observable<ListingTV_ModelDTO> getPopularTV(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET(Constants.TV_TOP_RATED)
    Observable<ListingTV_ModelDTO> getTopRatedTV(
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET(Constants.TV_SEARCH)
    Observable<ListingTV_ModelDTO> searchTv(
            @Query("query") String query,
            @Query("api_key") String apiKey,
            @Query("page") int page,
            @Query("language") String language
    );

//

//    @GET(Constants.MOVIE + "{id}/similar")
//    Observable<MovieResponse> getSimilarMovies(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.MOVIE + "{id}/recommendations")
//    Observable<MovieResponse> getRecommendations(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.MOVIE + "{id}/credits")
//    Observable<CreditsResponse> getCredits(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.MOVIE + "{id}")
//    Observable<MovieDetails> getMovieDetails(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.MOVIE + "{id}/videos")
//    Observable<VideoResponse> getMovieVideos(
//            @Path("id") String movieId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.PERSON + "{id}")
//    Observable<Person> getPerson(
//            @Path("id") int personId,
//            @Query("api_key") String apiKey
//    );
//
//    @GET(Constants.DISCOVER)
//    Observable<MovieResponse> getMoviesForCastID(
//            @Query("with_cast") int castId,
//            @Query("api_key") String apiKey
//    );
//



}
