package com.example.administrator.webviewlocationdemo;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = MainActivity.class.getSimpleName();

    private WebView mainWebView;
    private EditText addressET;
    private Button goBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        setListener();
    }

    private void initView() {

        mainWebView = (WebView) findViewById(R.id.wv_main);
        addressET = (EditText) findViewById(R.id.et_address);
        goBtn = (Button) findViewById(R.id.btn_go);
    }

    private void setListener() {
        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(addressET.getText().toString())) {
                    loadData();
                }
            }
        });
    }

    private void loadData() {
        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setGeolocationEnabled(true);
        webSettings.setGeolocationDatabasePath(getFilesDir().getPath());
//        mainWebView.setWebViewClient(new WebViewClient());
//        getLocation();

        mainWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onGeolocationPermissionsHidePrompt() {
                super.onGeolocationPermissionsHidePrompt();
                Log.i(LOGTAG, "onGeolocationPermissionsHidePrompt");
            }

            @Override
            public void onGeolocationPermissionsShowPrompt(final String origin,
                                                           final GeolocationPermissions.Callback callback) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Allow to access location information?");
                DialogInterface.OnClickListener dialogButtonOnClickListener = new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int clickedButton) {
                        if (DialogInterface.BUTTON_POSITIVE == clickedButton) {
                            callback.invoke(origin, true, true);
                        } else if (DialogInterface.BUTTON_NEGATIVE == clickedButton) {
                            callback.invoke(origin, false, false);
                        }
                    }
                };
                builder.setPositiveButton("Allow", dialogButtonOnClickListener);
                builder.setNegativeButton("Deny", dialogButtonOnClickListener);
                builder.show();
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                Log.i(LOGTAG, "onGeolocationPermissionsShowPrompt");
            }
        });

//        mainWebView.loadUrl(addressET.getText().toString());
        String path = "file:///android_asset/geolocation.html";
        mainWebView.loadUrl(path);
        testGeolocationOK();
        startLocationSettings();

    }

    private void testGeolocationOK() {
        LocationManager manager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        boolean gpsProviderOK = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean networkProviderOK = manager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean geolocationOK = gpsProviderOK && networkProviderOK;
        Log.i(LOGTAG, "gpsProviderOK = " + gpsProviderOK + "; networkProviderOK = " + networkProviderOK + "; geoLocationOK=" + geolocationOK);
    }

    private void startLocationSettings() {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }



    private class WebViewClient extends android.webkit.WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }
    }

    private void getLocation() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the location provider.
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Toast.makeText(MainActivity.this, "Can't open GPS", Toast.LENGTH_SHORT).show();
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);
    }

}
