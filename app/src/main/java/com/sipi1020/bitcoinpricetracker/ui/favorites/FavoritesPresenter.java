package com.sipi1020.bitcoinpricetracker.ui.favorites;

import android.content.Context;

import com.sipi1020.bitcoinpricetracker.BitcoinPriceTrackerApplication;
import com.sipi1020.bitcoinpricetracker.di.Network;
import com.sipi1020.bitcoinpricetracker.iteractor.FavoritesInteractor;
import com.sipi1020.bitcoinpricetracker.iteractor.events.GetFavoritesEvent;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.repository.Repository;
import com.sipi1020.bitcoinpricetracker.ui.Presenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

/**
 * Created by Viki on 2018-04-05.
 */

public class FavoritesPresenter extends Presenter<FavoritesScreen> {

    @Inject
    @Network
    Executor networkExecutor;

    @Inject
    FavoritesInteractor favoritesInteractor;

    @Inject
    Repository repository;

    @Inject
    Context context;


    @Override
    public void attachScreen(FavoritesScreen screen) {
        super.attachScreen(screen);
        BitcoinPriceTrackerApplication.injector.inject(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
        EventBus.getDefault().unregister(this);
    }

    public void refrehFavoriteList(){
        networkExecutor.execute(new Runnable() {
            @Override
            public void run() {
                favoritesInteractor.getFavorites();
            }
        });
    }

    public void showFavoriteRemoved(){
        screen.showFavoriteRemoved();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(final GetFavoritesEvent event) {
        if (event.getThrowable() != null) {
            event.getThrowable().printStackTrace();
            if (screen != null) {
                repository.open(context);
                List<TimeRangeData> data = repository.getFavorites();
                screen.reloadData(data);
                repository.close();
            }
        } else {
            if (screen != null) {
                if (event.getData() == null){
                    repository.open(context);
                    List<TimeRangeData> data = repository.getFavorites();
                    screen.reloadData(data);
                    repository.close();
                }
                else {
                    screen.reloadData(event.getData());
                }
            }
        }
    }

}
