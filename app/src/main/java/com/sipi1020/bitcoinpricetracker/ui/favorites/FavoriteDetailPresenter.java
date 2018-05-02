package com.sipi1020.bitcoinpricetracker.ui.favorites;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.ui.Presenter;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Viki on 2018-05-02.
 */

public class FavoriteDetailPresenter extends Presenter<FavoriteDetailScreen> {

    @Override
    public void attachScreen(FavoriteDetailScreen screen) {
        super.attachScreen(screen);
        BitcoinPriceTrackerApplication.injector.inject(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void loadData(TimeRangeData data){screen.reloadData(data);}
}
