package com.sipi1020.bitcoinpricetracker.ui.main;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.di.Network;
import com.sipi1020.bitcoinpricetracker.iteractor.PricesInteractor;
import com.sipi1020.bitcoinpricetracker.iteractor.events.GetPricesEvent;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Viki on 2018-04-05.
 */

public class MainPresenter extends Presenter<MainScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    PricesInteractor pricesInteractor;

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
        BitcoinPriceTrackerApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        EventBus.getDefault().unregister(this);
    }

    public void refreshPricesList(final Date start, final Date ends){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pricesInteractor.getPrices(start,ends);
            }
        });

    }

    public void showDefaultDates(Date start, Date end){
        screen.setDefaultDateValues(start,end);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetPricesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {

            }
        } else {
            if (screen != null) {
                screen.reloadList(event.getPrices());
            }
        }
    }

}
