package com.sipi1020.bitcoinpricetracker.ui.main;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.di.Network;
import com.sipi1020.bitcoinpricetracker.iteractor.FavoritesInteractor;
import com.sipi1020.bitcoinpricetracker.iteractor.PricesInteractor;
import com.sipi1020.bitcoinpricetracker.iteractor.events.GetPricesEvent;
import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
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

    @Inject
    FavoritesInteractor favoritesInteractor;

    TimeRangeData currentData;
    Date startDate;
    Date endDate;

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
        startDate= start;
        endDate = ends;
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

    public void initDatePickers(){
        screen.setDateListeners();
    }

    public void addToFavorite(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.saveFavorite(currentData);
                showFavoriteAdded();
            }
        });
    }

    public void showFavoriteAdded(){
        screen.showFavoriteAdded();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetPricesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {

            }
        } else {
            if (screen != null) {
                PricesResult result = event.getPrices();
                if (result != null) {
                    screen.reloadList(event.getPrices());
                    String start = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
                    String end = new SimpleDateFormat("yyyy-MM-dd").format(endDate);
                    currentData = new TimeRangeData(new Long(0), start, end, result.getPriceRecordList());
                }
            }
        }
    }

}
