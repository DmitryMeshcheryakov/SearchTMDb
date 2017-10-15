package by.insight.searchtmdb.view;


import by.insight.searchtmdb.presenter.vo.ResultDetailMovies;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;

public interface ActivityCallback {

    void startDetailMovieFragment(ResultListingMovies resultListingMovies);

    void showProgressBar();

    void hideProgressBar();

}
