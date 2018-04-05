package com.sipi1020.bitcoinpricetracker;

import android.app.Application;

import com.sipi1020.bitcoinpricetracker.view.UIModule;

/**
 * Created by Viki on 2018-04-05.
 */

public class BitcoinPriceTrackerApplication extends Application{

    public static BitcoinPriceTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerBitcoinPriceTrackerApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
