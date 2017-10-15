package by.insight.searchtmdb.presenter.mapper;

import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.model.listing_movies_model.ResultListingMoviesDTO;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;


public class RepoListMapper implements Function<List<ResultListingMoviesDTO>, List<ResultListingMovies>> {

    @Inject
    public RepoListMapper() {
    }

    @Override
    public List<ResultListingMovies> apply(@NonNull List<ResultListingMoviesDTO> resultPopularMoviesDTOs) throws Exception {
        if (resultPopularMoviesDTOs == null) {
            return null;
        }
        List<ResultListingMovies> resultListingMovies = Observable.fromIterable(resultPopularMoviesDTOs)
                .map(repoDTO -> new ResultListingMovies(repoDTO.getId(),
                        repoDTO.getTitle(),
                        repoDTO.getOverview(),
                        repoDTO.getBackdropPath(),
                        repoDTO.getVoteAverage()))
                .toList()
                .toObservable()
                .blockingFirst();
        return resultListingMovies;
    }
}
