package com.sipi1020.bitcoinpricetracker;

import android.app.Application;

import com.orm.SugarApp;
import com.sipi1020.bitcoinpricetracker.ui.UIModule;

/**
 * Created by Viki on 2018-04-05.
 */

public class BitcoinPriceTrackerApplication extends SugarApp{

    public static BitcoinPriceTrackerApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerBitcoinPriceTrackerApplicationComponent.builder().uIModule(new UIModule(this)).build();
    }
}
