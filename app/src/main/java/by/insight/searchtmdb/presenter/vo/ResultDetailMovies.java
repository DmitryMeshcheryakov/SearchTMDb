package by.insight.searchtmdb.presenter.vo;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import by.insight.searchtmdb.model.datail_movie_model.ProductionCompanyDTO;
import by.insight.searchtmdb.model.datail_movie_model.ProductionCountryDTO;
import by.insight.searchtmdb.model.datail_movie_model.SpokenLanguageDTO;



public class ResultDetailMovies implements Serializable {

    private Integer id;
    private String backdropPath;
    private Integer budget;
    private String originalLanguage;
    private String originalTitle;
    private String overview;
    private Double popularity;
    private Object posterPath;
    private List<ProductionCompanyDTO> productionCompanies = null;
    private List<ProductionCountryDTO> productionCountries = null;
    private String releaseDate;
    private Integer revenue;
    private Integer runtime;
    private List<SpokenLanguageDTO> spokenLanguages = null;
    private Double voteAverage;
    private Integer voteCount;

    public ResultDetailMovies(Integer id, String backdropPath, Integer budget, String originalLanguage, String originalTitle, String overview, Double popularity, Object posterPath, List<ProductionCompanyDTO> productionCompanies, List<ProductionCountryDTO> productionCountries, String releaseDate, Integer revenue, Integer runtime, List<SpokenLanguageDTO> spokenLanguages, Double voteAverage, Integer voteCount) {
        this.id = id;
        this.backdropPath = backdropPath;
        this.budget = budget;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.popularity = popularity;
        this.posterPath = posterPath;
        this.productionCompanies = productionCompanies;
        this.productionCountries = productionCountries;
        this.releaseDate = releaseDate;
        this.revenue = revenue;
        this.runtime = runtime;
        this.spokenLanguages = spokenLanguages;
        this.voteAverage = voteAverage;
        this.voteCount = voteCount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Object getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(Object posterPath) {
        this.posterPath = posterPath;
    }

    public List<ProductionCompanyDTO> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompanyDTO> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<ProductionCountryDTO> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountryDTO> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<SpokenLanguageDTO> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguageDTO> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResultDetailMovies that = (ResultDetailMovies) o;

        if (!id.equals(that.id)) return false;
        if (!backdropPath.equals(that.backdropPath)) return false;
        if (!budget.equals(that.budget)) return false;
        if (!originalLanguage.equals(that.originalLanguage)) return false;
        if (!originalTitle.equals(that.originalTitle)) return false;
        if (!overview.equals(that.overview)) return false;
        if (!popularity.equals(that.popularity)) return false;
        if (!posterPath.equals(that.posterPath)) return false;
        if (!productionCompanies.equals(that.productionCompanies)) return false;
        if (!productionCountries.equals(that.productionCountries)) return false;
        if (!releaseDate.equals(that.releaseDate)) return false;
        if (!revenue.equals(that.revenue)) return false;
        if (!runtime.equals(that.runtime)) return false;
        if (!spokenLanguages.equals(that.spokenLanguages)) return false;
        if (!voteAverage.equals(that.voteAverage)) return false;
        return voteCount.equals(that.voteCount);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + backdropPath.hashCode();
        result = 31 * result + budget.hashCode();
        result = 31 * result + originalLanguage.hashCode();
        result = 31 * result + originalTitle.hashCode();
        result = 31 * result + overview.hashCode();
        result = 31 * result + popularity.hashCode();
        result = 31 * result + posterPath.hashCode();
        result = 31 * result + productionCompanies.hashCode();
        result = 31 * result + productionCountries.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + revenue.hashCode();
        result = 31 * result + runtime.hashCode();
        result = 31 * result + spokenLanguages.hashCode();
        result = 31 * result + voteAverage.hashCode();
        result = 31 * result + voteCount.hashCode();
        return result;
    }
}
