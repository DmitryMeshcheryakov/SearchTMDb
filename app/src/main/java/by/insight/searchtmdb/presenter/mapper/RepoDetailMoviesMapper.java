package by.insight.searchtmdb.presenter.mapper;

import javax.inject.Inject;

import by.insight.searchtmdb.model.datail_movie_model.DetailMoviesDTO;
import by.insight.searchtmdb.presenter.vo.ResultDetailMovies;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Dmitry on 14.10.2017.
 */

public class RepoDetailMoviesMapper implements Function<DetailMoviesDTO, ResultDetailMovies> {

    @Inject
    public RepoDetailMoviesMapper() {
    }

    @Override
    public ResultDetailMovies apply(@NonNull DetailMoviesDTO detailMoviesDTO) throws Exception {
        return new ResultDetailMovies(detailMoviesDTO.getId(),
                detailMoviesDTO.getBackdropPath(),
                detailMoviesDTO.getBudget(),
                detailMoviesDTO.getOriginalLanguage(),
                detailMoviesDTO.getOriginalTitle(),
                detailMoviesDTO.getOverview(),
                detailMoviesDTO.getPopularity(),
                detailMoviesDTO.getPosterPath(),
                detailMoviesDTO.getProductionCompanies(),
                detailMoviesDTO.getProductionCountries(),
                detailMoviesDTO.getReleaseDate(),
                detailMoviesDTO.getRevenue(),
                detailMoviesDTO.getRuntime(),
                detailMoviesDTO.getSpokenLanguages(),
                detailMoviesDTO.getVoteAverage(),
                detailMoviesDTO.getVoteCount());
    }
}
