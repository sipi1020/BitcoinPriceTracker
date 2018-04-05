package com.sipi1020.bitcoinpricetracker.presenter;

import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.view.screen.MainScreen;

import java.util.Date;
import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */

public class MainPresenter extends Presenter<MainScreen>{

    @Override
    public void attachScreen(MainScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showPricesList(List<PriceRecord> records){
        screen.reloadList(records);
    }

    public void showDefaultDates(Date start, Date end){
        screen.setDefaultDateValues(start,end);
    }

}
