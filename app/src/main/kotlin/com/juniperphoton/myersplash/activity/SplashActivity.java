package com.juniperphoton.myersplash.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.juniperphoton.myersplash.R;

public class SplashActivity extends AppCompatActivity {

    private static final int DELAY = 3000;

    private InterstitialAd interstitialAd;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (interstitialAd.isLoaded()) {
                displayInterstitial();
            } else {
                nextActivity();
            }
        }
    };


    private void displayInterstitial() {

        if (interstitialAd != null) {

            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
            }
        }
    }

    private void setUpInterstitialAd() {
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_interstitial_id));
        AdRequest.Builder request = new AdRequest.Builder();

        interstitialAd.loadAd(request.build());
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                nextActivity();
            }


        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpInterstitialAd();
        handler.postDelayed(runnable, DELAY);
    }

    private void removeCallback() {
        if (handler != null) {
            handler.removeCallbacks(runnable);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        removeCallback();
        interstitialAd = null;
    }

    @Override
    protected void onStop() {
        super.onStop();
        removeCallback();
    }

    private void nextActivity() {
        startActivity(new Intent(this, MainActivity.class));

    }


}
