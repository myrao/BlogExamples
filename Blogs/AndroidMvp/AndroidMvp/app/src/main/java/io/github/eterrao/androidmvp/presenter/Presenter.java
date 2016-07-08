package io.github.eterrao.androidmvp.presenter;

/**
 * Created by raomengyang on 6/25/16.
 */
public interface Presenter<V> {

    void attachView(V view);

    void detachView();
}
