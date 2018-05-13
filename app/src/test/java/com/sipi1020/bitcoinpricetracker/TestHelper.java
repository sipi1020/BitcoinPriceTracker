package com.sipi1020.bitcoinpricetracker;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

/**
 * Created by Viki on 2018-05-02.
 */

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        BitcoinPriceTrackerApplication application = (BitcoinPriceTrackerApplication) RuntimeEnvironment.application;
        BitcoinPriceTrackerApplicationComponent injector = DaggerTestComponent.builder()
                .testModule(new TestModule(application.getApplicationContext())).build();
        application.injector = injector;
    }
}

