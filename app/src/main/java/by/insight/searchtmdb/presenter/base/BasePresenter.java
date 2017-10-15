package by.insight.searchtmdb.presenter.base;

import javax.inject.Inject;

import by.insight.searchtmdb.App;
import by.insight.searchtmdb.model.base.Model;
import by.insight.searchtmdb.view.fragments.base.View;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public abstract class BasePresenter implements Presenter {

    @Inject
    protected Model model;

    @Inject
    CompositeDisposable mCompositeDisposable;

    protected BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addDisposable(Disposable disposable) {
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        mCompositeDisposable.clear();
    }

    protected abstract View getView();

    protected void showLoadingState() {
        getView().showLoading();
    }

    protected void hideLoadingState() {
        getView().hideLoading();
    }

    protected void showError(Throwable e) {
        getView().showError(e.getMessage());
    }

}
