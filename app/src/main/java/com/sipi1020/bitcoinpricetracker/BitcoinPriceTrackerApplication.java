package com.sipi1020.bitcoinpricetracker;



import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.orm.SugarApp;
import com.sipi1020.bitcoinpricetracker.ui.UIModule;

/**
 * Created by Viki on 2018-04-05.
 */

public class BitcoinPriceTrackerApplication extends SugarApp{

    public static BitcoinPriceTrackerApplicationComponent injector;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerBitcoinPriceTrackerApplicationComponent.builder().uIModule(new UIModule(this)).build();
        sAnalytics = GoogleAnalytics.getInstance(this);
    }

    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }

}
