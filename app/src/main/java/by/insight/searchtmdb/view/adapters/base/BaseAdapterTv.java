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



public abstract class BaseAdapterTv<T> extends RecyclerView.Adapter<BaseAdapterTv.ViewHolder> {

    protected List<T> list;


    public BaseAdapterTv(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }



    @Override
    public BaseAdapterTv.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.abstract_item, viewGroup, false);
        return new BaseAdapterTv.ViewHolder(v);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_popular_movies)
        public
        ImageView mImageTv;
        @Bind(R.id.title_popular_movies)
        public
        TextView mNameTv;
        @Bind(R.id.rating_popular_movies)
        public
        TextView mRatingTv;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
