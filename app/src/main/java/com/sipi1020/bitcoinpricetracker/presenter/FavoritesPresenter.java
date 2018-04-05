package com.sipi1020.bitcoinpricetracker.presenter;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.view.screen.FavoritesScreen;

import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */

public class FavoritesPresenter extends Presenter<FavoritesScreen> {

    @Override
    public void attachScreen(FavoritesScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showTimeRangeList(List<TimeRangeData> data){
        screen.reloadData(data);
    }
}
