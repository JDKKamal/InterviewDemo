package com.jdkgroup.baseclass;

import com.jdkgroup.interacter.AppInteractor;

public abstract class BasePresenter<V extends BaseView> {
    private V view;
    protected AppInteractor appInteractor;

    final void attachView(V view) {
        this.view = view;
    }

    public V getView() {
        return view;
    }

    public boolean hasInternet() {
        return view.hasInternet();
    }

    public boolean hasInternetWithoutMessage(){
        return view.hasInternetWithoutMessage();
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before requesting data to the Presenter");
        }
    }

    protected final AppInteractor getAppInteractor() {
        if (appInteractor == null) {
            appInteractor = new AppInteractor();
        }
        return appInteractor;
    }
}
