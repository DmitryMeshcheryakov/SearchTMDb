package by.insight.searchtmdb.view.fragments.listing_movies;

import java.util.List;

import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.view.fragments.base.View;

/**
 * Created by Dmitry on 13.10.2017.
 */

public interface SearchMoviesView extends View {

    void showSearchListingMovies(List<ResultListingMovies> resultListingMovies);

    void showAddSearch(List<ResultListingMovies> resultListingMovies);

    void showEmptyList();
}
