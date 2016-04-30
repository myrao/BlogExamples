package com.example.administrator.myanimationdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private LinearLayout translateLL;
    private LinearLayout translateLL2;
    private Button moveBtn;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setListener();
    }

    private void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        translateLL = (LinearLayout) findViewById(R.id.ll_translate);
//        translateLL2 = (LinearLayout) findViewById(R.id.ll_translate2);
        moveBtn = (Button) findViewById(R.id.button);
    }

    private void setListener() {
        moveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("WTF", "onclick");
                doTranslateAnimation(translateLL);
            }
        });
    }

    private void doTranslateAnimation(View view) {
        TranslateAnimation ta = new TranslateAnimation(0, -100, 0, 0);
        ta.setDuration(1000);
        ta.setRepeatMode(TranslateAnimation.RESTART);
        ta.setRepeatCount(Integer.MAX_VALUE);
        view.startAnimation(ta);
    }
}
