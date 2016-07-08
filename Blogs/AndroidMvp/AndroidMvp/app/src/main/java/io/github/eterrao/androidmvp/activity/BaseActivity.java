package io.github.eterrao.androidmvp.activity;

import android.app.Activity;
import android.os.Bundle;

import io.github.eterrao.androidmvp.presenter.BasePresenter;

/**
 * Created by raomengyang on 6/25/16.
 */
public abstract class BaseActivity<P extends BasePresenter> extends Activity {

    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        presenter = initPresenter();
        super.onCreate(savedInstanceState);
        initViews();
    }

    protected abstract P initPresenter();

    protected abstract void initViews();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) presenter.detachView();
    }
}
