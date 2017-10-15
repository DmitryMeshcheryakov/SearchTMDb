package by.insight.searchtmdb.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.ybq.android.spinkit.SpinKitView;
import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import butterknife.Bind;

import butterknife.ButterKnife;
import by.insight.searchtmdb.Constants;
import by.insight.searchtmdb.R;
import by.insight.searchtmdb.TypeData;
import by.insight.searchtmdb.presenter.vo.ResultListingMovies;
import by.insight.searchtmdb.tools.InitUtil;
import by.insight.searchtmdb.view.fragments.listing_movies.DetailMoviesFragment;
import by.insight.searchtmdb.view.fragments.listing_movies.ListingMoviesFragment;
import by.insight.searchtmdb.view.fragments.MenuDrawFragment;
import by.insight.searchtmdb.view.fragments.listing_movies.SearchMoviesFragment;
import by.insight.searchtmdb.view.fragments.listing_tv.ListingTvFragment;

public class MainActivity extends AppCompatActivity
        implements ActivityCallback,
        MenuDrawFragment.onClickTypeMenuList {

//    private static String TAG = "TAG";
    @Bind(R.id.drawerlayout)
    FlowingDrawer mDrawer;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.progress_bar)
    SpinKitView mSpinKitView;



    private FragmentManager fragmentManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        mSpinKitView.setColor(R.color.style_color_accent);
        setupToolbar();
        setupMenu();
        fragmentManager = getSupportFragmentManager();

//        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
//        if (fragment == null)
//            replaceFragmentAndBundle(new ListingMoviesFragment(), false, Constants.KEY_MOVIE, TypeData.POPULAR_MOVIES);
    }

    protected void setupToolbar() {
//        setSupportActionBar(mToolbar);
        InitUtil.initToolbar(mToolbar, this);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_white);
        mToolbar.setNavigationOnClickListener(v -> mDrawer.toggleMenu());
    }


    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuDrawFragment mMenuFragment = (MenuDrawFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuDrawFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }


    private void replaceFragmentAndBundle(Fragment fragment, boolean addBackStack, String key, String value) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        Bundle bundle = new Bundle();
        bundle.putString(key, value);
        fragment.setArguments(bundle);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void startDetailMovieFragment(ResultListingMovies resultListingMovies) {
        replaceFragment(DetailMoviesFragment.newInstance(resultListingMovies), true);

    }

    @Override
    public void showProgressBar() {
        mSpinKitView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mSpinKitView.setVisibility(View.INVISIBLE);
    }


    @Override
    public void onClickMenuList(String type) {

        switch (type) {
            case TypeData.SEARCH_MOVIE: {
                replaceFragment(new SearchMoviesFragment(), false);
                closeDrawerMenu();
                break;
            }
            case TypeData.POPULAR_MOVIES: {
                replaceFragmentAndBundle(new ListingMoviesFragment(), false, Constants.KEY_MOVIE, TypeData.POPULAR_MOVIES);
                closeDrawerMenu();
                break;
            }
            case TypeData.TOP_RATED_MOVIES: {
                replaceFragmentAndBundle(new ListingMoviesFragment(), false, Constants.KEY_MOVIE, TypeData.TOP_RATED_MOVIES);
                closeDrawerMenu();
                break;
            }
            case TypeData.NOW_PLAYING_MOVIES: {
                replaceFragmentAndBundle(new ListingMoviesFragment(), false, Constants.KEY_MOVIE, TypeData.NOW_PLAYING_MOVIES);
                closeDrawerMenu();
                break;
            }case TypeData.UPCOMING_MOVIES:
            {
                replaceFragmentAndBundle(new ListingMoviesFragment(), false, Constants.KEY_MOVIE, TypeData.UPCOMING_MOVIES);
                closeDrawerMenu();
                break;
            }case TypeData.TOP_RATED_TV:
            {
                replaceFragmentAndBundle(new ListingTvFragment(), false, Constants.KEY_TV, TypeData.TOP_RATED_TV);
                closeDrawerMenu();
                break;
            }case TypeData.POPULAR_TV:
            {
                replaceFragmentAndBundle(new ListingTvFragment(), false, Constants.KEY_TV, TypeData.POPULAR_TV);
                closeDrawerMenu();
                break;
            }

            default: {

            }
        }
    }

    private void closeDrawerMenu() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        }
    }

}
