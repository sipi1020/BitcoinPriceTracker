package com.sipi1020.bitcoinpricetracker.iteractor;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Viki on 2018-04-05.
 */

@Module
public class InteractorModule {

    @Provides
    public PricesInteractor providePriesInteractor(){
        return new PricesInteractor();
    }

    @Provides FavoritesInteractor provideFavoritesInteractor (){
        return new FavoritesInteractor();
    }
}
