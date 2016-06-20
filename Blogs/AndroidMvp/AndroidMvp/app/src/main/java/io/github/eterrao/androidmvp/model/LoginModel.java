package io.github.eterrao.androidmvp.model;

import io.github.eterrao.androidmvp.listener.OnLoginStatusListener;
import io.github.eterrao.androidmvp.utils.HttpUtils;

/**
 * Created by raomengyang on 6/21/16.
 */
public class LoginModel implements ILoginModel {

    @Override
    public void sendMsg(String msg, OnLoginStatusListener onLoginStatusListener) {
        // TODO: send your msg code to server
        HttpUtils.doPostRequest();
    }

    @Override
    public void checkMsg(String msg, OnLoginStatusListener onLoginStatusListener) {
        // TODO: check your msg code from server
    }
}
