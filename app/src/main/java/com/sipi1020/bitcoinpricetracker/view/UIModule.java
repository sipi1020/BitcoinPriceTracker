package com.sipi1020.bitcoinpricetracker.view;

import android.content.Context;

import com.sipi1020.bitcoinpricetracker.presenter.AboutPresenter;
import com.sipi1020.bitcoinpricetracker.presenter.FavoritesPresenter;
import com.sipi1020.bitcoinpricetracker.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Viki on 2018-04-05.
 */

@Module
public class UIModule {

    private Context context;

    public UIModule(Context context) {
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

    @Provides
    @Singleton
    public FavoritesPresenter provideFavoritesPresenter(){
        return new FavoritesPresenter();
    }

    @Provides
    @Singleton
    public AboutPresenter provideAboutPresenter(){
        return new AboutPresenter();
    }
}
