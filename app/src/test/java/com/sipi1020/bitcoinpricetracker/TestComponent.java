package com.sipi1020.bitcoinpricetracker;

import com.sipi1020.bitcoinpricetracker.iteractor.InteractorModule;
import com.sipi1020.bitcoinpricetracker.mock.MockNetworkModule;
import com.sipi1020.bitcoinpricetracker.repository.RepositoryModule;
import com.sipi1020.bitcoinpricetracker.ui.UIModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Viki on 2018-05-02.
 */

@Singleton
@Component(modules = {MockNetworkModule.class, TestModule.class, InteractorModule.class, RepositoryModule.class})
public interface TestComponent extends BitcoinPriceTrackerApplicationComponent {

}

