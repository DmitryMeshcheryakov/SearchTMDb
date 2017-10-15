package by.insight.searchtmdb.view.fragments.listing_movies;


import java.util.List;

import by.insight.searchtmdb.presenter.vo.ResultDetailMovies;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.fragments.base.View;

public interface ListingMoviesView extends View {

    void showListingMovies(List<ResultListingMovies> resultListingMovies);

    void showEmptyList();

    void startDetailMovieFragment(ResultListingMovies resultListingMovies);

}
