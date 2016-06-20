package io.github.eterrao.androidmvp.model;

import io.github.eterrao.androidmvp.listener.OnLoginStatusListener;

/**
 * Created by raomengyang on 6/21/16.
 */
public interface ILoginModel {

    void sendMsg(String msg, OnLoginStatusListener onLoginStatusListener);

    void checkMsg(String msg, OnLoginStatusListener onLoginStatusListener);
}
