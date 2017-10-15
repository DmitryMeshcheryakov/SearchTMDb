
package by.insight.searchtmdb.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import by.insight.searchtmdb.R;
import by.insight.searchtmdb.TypeData;


public class MenuDrawFragment extends Fragment {

    public interface onClickTypeMenuList {
        void onClickMenuList(String type);
    }

    private onClickTypeMenuList mOnClickTypeMenuList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_drawer_fragment, container,
                false);
        NavigationView vNavigation = (NavigationView) view.findViewById(R.id.vNavigation);
        vNavigation.setNavigationItemSelectedListener(menuItem -> {
            Map<String, String> map = new HashMap<String, String>();
            map.put(getString(R.string.popular_movies), TypeData.POPULAR_MOVIES);
            map.put(getString(R.string.top_rated_movies), TypeData.TOP_RATED_MOVIES);
            map.put(getString(R.string.now_playing_movies), TypeData.NOW_PLAYING_MOVIES);
            map.put(getString(R.string.upcoming_movies), TypeData.UPCOMING_MOVIES);
            map.put(getString(R.string.search_movies), TypeData.SEARCH_MOVIE);
            map.put(getString(R.string.top_rated_tv), TypeData.TOP_RATED_TV);
            map.put(getString(R.string.popular_tv), TypeData.POPULAR_TV);
            for(Map.Entry<String, String> pain : map.entrySet()) {
                if(pain.getKey().contains(menuItem.getTitle()))
                mOnClickTypeMenuList.onClickMenuList(pain.getValue());
            }
            return false;
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mOnClickTypeMenuList = (onClickTypeMenuList) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mOnClickTypeMenuList = null;
    }
}
