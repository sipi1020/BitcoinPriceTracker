package com.sipi1020.bitcoinpricetracker.tests;

import com.sipi1020.bitcoinpricetracker.BuildConfig;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoriteDetailPresenter;
import com.sipi1020.bitcoinpricetracker.ui.favorites.FavoriteDetailScreen;
import com.sipi1020.bitcoinpricetracker.ui.main.MainPresenter;
import com.sipi1020.bitcoinpricetracker.ui.main.MainScreen;

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
public class FavoriteDetailTest {

    private FavoriteDetailPresenter presenter;
    private FavoriteDetailScreen screen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        screen = mock(FavoriteDetailScreen.class);
        presenter = new FavoriteDetailPresenter();
        presenter.attachScreen(screen);
    }


    @Test
    public void testReloadList() {
        TimeRangeData data = new TimeRangeData();
        presenter.loadData(data);
        verify(screen).reloadData(data);
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}
