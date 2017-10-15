package by.insight.searchtmdb.presenter.mapper;

import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.model.listing_movies_model.ListingMoviesModelDTO;
import by.insight.searchtmdb.model.listing_movies_model.ResultListingMoviesDTO;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;



public class RepoObjectMapper implements Function<ListingMoviesModelDTO, List<ResultListingMoviesDTO>> {

    @Inject
    public RepoObjectMapper() {
    }

    @Override
    public List<ResultListingMoviesDTO> apply(@NonNull ListingMoviesModelDTO popularMoviesModelDTO) throws Exception {
        if(popularMoviesModelDTO == null) return null;
        return popularMoviesModelDTO.getResults();
    }
}
