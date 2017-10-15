package by.insight.searchtmdb.presenter.mapper;

import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.model.listing_tv_model.ListingTV_ModelDTO;
import by.insight.searchtmdb.model.listing_tv_model.ResultListingTV_ModelDTO;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Dmitry on 13.10.2017.
 */

public class RepoTvObjectMapper implements Function<ListingTV_ModelDTO, List<ResultListingTV_ModelDTO>> {

    @Inject
    public RepoTvObjectMapper() {
    }

    @Override
    public List<ResultListingTV_ModelDTO> apply(@NonNull ListingTV_ModelDTO listingTV_modelDTO) throws Exception {
        if(listingTV_modelDTO == null) return null;
        return listingTV_modelDTO.getResults();
    }
}
