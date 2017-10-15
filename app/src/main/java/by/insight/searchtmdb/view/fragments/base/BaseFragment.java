package by.insight.searchtmdb.view.fragments.base;

import android.app.Activity;
import android.support.v4.app.Fragment;

import by.insight.searchtmdb.presenter.base.Presenter;
import by.insight.searchtmdb.view.ActivityCallback;


public abstract class BaseFragment extends Fragment implements View{

    protected ActivityCallback activityCallback;

    protected abstract Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            activityCallback = (ActivityCallback) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement activityCallback");
        }

    }

    @Override
    public void showLoading() {
        activityCallback.showProgressBar();
    }

    @Override
    public void hideLoading() {
        activityCallback.hideProgressBar();
    }

}

