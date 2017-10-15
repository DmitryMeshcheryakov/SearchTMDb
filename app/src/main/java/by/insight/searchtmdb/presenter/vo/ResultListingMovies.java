package by.insight.searchtmdb.presenter.vo;

import java.io.Serializable;



public class ResultListingMovies implements Serializable {
    private Integer id;
    private String title;
    private String overview;
    private String posterPath;
    private Double voteAverage;

    public ResultListingMovies(Integer id, String title, String overview, String posterPath, Double voteAverage) {
        this.id = id;
        this.title = title;
        this.overview = overview;
        this.posterPath = posterPath;
        this.voteAverage = voteAverage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

        ResultListingMovies that = (ResultListingMovies) o;

        if (!id.equals(that.id)) return false;
        if (!title.equals(that.title)) return false;
        if (!overview.equals(that.overview)) return false;
        if (!posterPath.equals(that.posterPath)) return false;
        return voteAverage.equals(that.voteAverage);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + posterPath.hashCode();
        result = 31 * result + voteAverage.hashCode();
        return result;
    }
}
