package com.sipi1020.bitcoinpricetracker;

import com.sipi1020.bitcoinpricetracker.iteractor.FavoritesInteractor;
import com.sipi1020.bitcoinpricetracker.iteractor.InteractorModule;
import com.sipi1020.bitcoinpricetracker.iteractor.PricesInteractor;
import com.sipi1020.bitcoinpricetracker.networking.NetworkModule;
import com.sipi1020.bitcoinpricetracker.repository.RepositoryModule;
import com.sipi1020.bitcoinpricetracker.ui.about.AboutFragment;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoriteDetailFragment;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoriteDetailPresenter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesAdapter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesFragment;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesPresenter;
import com.sipi1020.bitcoinpricetracker.ui.main.MainFragment;
import com.sipi1020.bitcoinpricetracker.ui.UIModule;
import com.sipi1020.bitcoinpricetracker.ui.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Viki on 2018-04-05.
 */
@Singleton
@Component(modules = {NetworkModule.class, InteractorModule.class, UIModule.class, RepositoryModule.class})
public interface BitcoinPriceTrackerApplicationComponent {

    void inject(PricesInteractor pricesInteractor);
    void inject(FavoritesInteractor favoritesInteractor);
    void inject(MainFragment mainFragment);
    void inject(FavoritesFragment favoritesFragment);
    void inject(AboutFragment aboutFragment);
    void inject(FavoriteDetailFragment favoriteDetailFragment);
    void inject(MainPresenter mainPresenter);
    void inject(FavoritesPresenter favoritesPresenter);
    void inject(FavoritesAdapter favoritesAdapter);
    void inject(FavoriteDetailPresenter favoriteDetailPresenter);
}
