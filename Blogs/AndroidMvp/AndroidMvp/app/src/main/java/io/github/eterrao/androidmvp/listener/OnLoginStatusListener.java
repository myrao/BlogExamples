package io.github.eterrao.androidmvp.listener;

/**
 * Created by raomengyang on 6/21/16.
 */
public interface OnLoginStatusListener {

    void onSendError();

    void onSendSuccess();

    void onCheckError();

    void onCheckSuccess();
}
