package by.insight.searchtmdb.presenter.vo;

import java.io.Serializable;

/**
 * Created by Dmitry on 13.10.2017.
 */

public class ResultListingTv implements Serializable{

    private String name;
    private String overview;
    private String posterPath;
    private Double voteAverage;

    public ResultListingTv(String name, String overview, String posterPath, Double voteAverage) {
        this.name = name;
        this.overview = overview;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultListingTv that = (ResultListingTv) o;

        if (!name.equals(that.name)) return false;
        if (!overview.equals(that.overview)) return false;
        if (!posterPath.equals(that.posterPath)) return false;
        return voteAverage.equals(that.voteAverage);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + posterPath.hashCode();
        result = 31 * result + voteAverage.hashCode();
        return result;
    }
}
