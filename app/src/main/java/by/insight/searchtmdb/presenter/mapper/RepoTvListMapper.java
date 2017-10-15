package by.insight.searchtmdb.presenter.mapper;

import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.model.listing_tv_model.ResultListingTV_ModelDTO;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.presenter.vo.ResultListingTv;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by Dmitry on 13.10.2017.
 */

public class RepoTvListMapper implements Function<List<ResultListingTV_ModelDTO>, List<ResultListingTv>> {
    @Inject
    public RepoTvListMapper() {
    }

    @Override
    public List<ResultListingTv> apply(@NonNull List<ResultListingTV_ModelDTO> resultListingTV_modelDTOs) throws Exception {
        if (resultListingTV_modelDTOs == null) {
            return null;
        }
        List<ResultListingTv> resultListingTvs = Observable.fromIterable(resultListingTV_modelDTOs)
                .map(repoDTO -> new ResultListingTv(repoDTO.getName(), repoDTO.getOverview(), repoDTO.getBackdropPath(), repoDTO.getVoteAverage()))
                .toList()
                .toObservable()
                .blockingFirst();
        return resultListingTvs;
    }
}
