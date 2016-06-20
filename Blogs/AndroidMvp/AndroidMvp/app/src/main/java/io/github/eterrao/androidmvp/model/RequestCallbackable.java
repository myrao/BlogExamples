package io.github.eterrao.androidmvp.model;

/**
 * Created by raomengyang on 6/21/16.
 */
public interface RequestCallbackable<T> {

    void requestSuccess(T object);

    void requestError(T object);
}
