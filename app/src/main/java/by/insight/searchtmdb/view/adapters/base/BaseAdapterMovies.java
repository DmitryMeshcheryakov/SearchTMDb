package by.insight.searchtmdb.view.adapters.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;

import butterknife.ButterKnife;
import by.insight.searchtmdb.R;

public abstract class BaseAdapterMovies<T> extends RecyclerView.Adapter<BaseAdapterMovies.ViewHolder> {

    protected List<T> list;


    public BaseAdapterMovies(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.abstract_item, viewGroup, false);
        return new ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_popular_movies)
        public
        ImageView mImagePopularMovies;
        @Bind(R.id.title_popular_movies)
        public
        TextView mTitlePopularMovies;
        @Bind(R.id.rating_popular_movies)
        public
        TextView mRatingPopularMovies;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
