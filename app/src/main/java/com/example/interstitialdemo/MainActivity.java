package com.example.interstitialdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.OnPaidEventListener;
import com.google.android.gms.ads.ResponseInfo;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {

    InterstitialAd minterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();

       InterstitialAd.load(this, "ca-app-pub-3940256099942544/1033173712", adRequest,
               new InterstitialAdLoadCallback() {
                   @Override
                   public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                       super.onAdFailedToLoad(loadAdError);
                       minterstitialAd = null;
                   }
                   @Override
                   public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                       super.onAdLoaded(interstitialAd);
                       minterstitialAd = interstitialAd;
                   }
               });

        if (minterstitialAd != null) {
            minterstitialAd.show(MainActivity.this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }

    }
}