package by.insight.searchtmdb.model.listing_tv_model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ListingTV_ModelDTO {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("results")
        @Expose
        private List<ResultListingTV_ModelDTO> results = null;
        @SerializedName("total_results")
        @Expose
        private Integer totalResults;
        @SerializedName("total_pages")
        @Expose
        private Integer totalPages;

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public List<ResultListingTV_ModelDTO> getResults() {
            return results;
        }

        public void setResults(List<ResultListingTV_ModelDTO> results) {
            this.results = results;
        }

        public Integer getTotalResults() {
            return totalResults;
        }

        public void setTotalResults(Integer totalResults) {
            this.totalResults = totalResults;
        }

        public Integer getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(Integer totalPages) {
            this.totalPages = totalPages;
        }
}
