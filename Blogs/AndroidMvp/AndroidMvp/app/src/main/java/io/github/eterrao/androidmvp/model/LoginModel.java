package io.github.eterrao.androidmvp.model;

import io.github.eterrao.androidmvp.listener.OnLoginStatusListener;
import io.github.eterrao.androidmvp.utils.HttpUtils;

/**
 * Created by raomengyang on 6/21/16.
 */
public class LoginModel implements ILoginModel {

    @Override
    public void sendMsg(String msg, final OnLoginStatusListener onLoginStatusListener) {
        // TODO: send your msg code to server
        String yoursUrl = "";
        String key = "";
        String value = "";
        HttpUtils.doPostRequest(yoursUrl, new RequestCallbackable() {
                    @Override
                    public void requestSuccess(Object object) {
                        onLoginStatusListener.onSendSuccess();
                    }

                    @Override
                    public void requestError(Object object) {
                        onLoginStatusListener.onSendError();
                    }
                },
                key, value /*key1, value1, key2, value2...*/);
    }

    @Override
    public void checkMsg(String msg, OnLoginStatusListener onLoginStatusListener) {
        // TODO: check your msg code from server
    }
}
