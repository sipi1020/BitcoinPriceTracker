package com.sipi1020.bitcoinpricetracker.tests;

import com.sipi1020.bitcoinpricetracker.BuildConfig;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesPresenter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoritesScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static com.sipi1020.bitcoinpricetracker.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Viki on 2018-05-03.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class FavoritesTest {

    private FavoritesPresenter presenter;
    private FavoritesScreen screen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        screen = mock(FavoritesScreen.class);
        presenter = new FavoritesPresenter();
        presenter.attachScreen(screen);
    }


    @Test
    public void testReloadList() {
        presenter.refrehFavoriteList();
        verify(screen).reloadData(presenter.data);
    }

    @Test
    public void removeFavorite(){
        presenter.removeFavorite(new Long(1));
        verify(screen).showFavoriteRemoved();
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}
