package com.sipi1020.bitcoinpricetracker.tests;

import com.sipi1020.bitcoinpricetracker.BuildConfig;
import com.sipi1020.bitcoinpricetracker.ui.about.AboutPresenter;
import com.sipi1020.bitcoinpricetracker.ui.about.AboutScreen;

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
public class AboutTest {

    private AboutPresenter presenter;
    private AboutScreen screen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        screen = mock(AboutScreen.class);
        presenter = new AboutPresenter();
        presenter.attachScreen(screen);
    }


    @Test
    public void testOpenDeveloperWebsite() {
        String url  = "https://github.com/sipi1020";
        presenter.showDeveloperWebsite(url);
        verify(screen).openDeveloperSite(url);
    }

    @After
    public void tearDown() {
        presenter.detachScreen();
    }
}
