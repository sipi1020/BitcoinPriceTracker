package com.sipi1020.bitcoinpricetracker;

import android.content.Context;

import com.sipi1020.bitcoinpricetracker.di.Network;
import com.sipi1020.bitcoinpricetracker.ui.about.AboutPresenter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoriteDetailPresenter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesPresenter;
import com.sipi1020.bitcoinpricetracker.ui.main.MainPresenter;
import com.sipi1020.bitcoinpricetracker.util.UiExecutor;

import java.util.concurrent.Executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Viki on 2018-05-02.
 */

@Module
public class TestModule {

    private Context context;

    public TestModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

    @Provides
    @Singleton
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Singleton
    @Provides
    public AboutPresenter provideAboutPresenter() {
        return new AboutPresenter();
    }

    @Provides
    @Singleton
    public FavoritesPresenter provideFavoritesPresenter() {
        return new FavoritesPresenter();
    }

    @Provides
    @Singleton
    public FavoriteDetailPresenter provideFavoritesDetailPresenter() {
        return new FavoriteDetailPresenter();
    }

    @Provides
    @Singleton
    @Network
    public Executor provideNetworkExecutor() {
        return new UiExecutor();
    }


}