package com.sipi1020.bitcoinpricetracker.repository;

/**
 * Created by Viki on 2018-04-20.
 */

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public Repository provideRepository() {
        return new SugarOrmRepository();
    }
}