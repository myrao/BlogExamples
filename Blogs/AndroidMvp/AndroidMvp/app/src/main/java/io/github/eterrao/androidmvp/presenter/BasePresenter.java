package io.github.eterrao.androidmvp.presenter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * Created by raomengyang on 6/25/16.
 */
public abstract class BasePresenter<V> implements Presenter<V> {

    private Reference<V> viewReference;

    @Override
    public void attachView(V view) {
        viewReference = new WeakReference<V>(view);
    }

    protected V getView() {
        return viewReference.get();
    }

    @Override
    public void detachView() {
        if (viewReference != null) {
            viewReference.clear();
            viewReference = null;
        }
    }
}
