package com.sipi1020.bitcoinpricetracker.tests;

import com.sipi1020.bitcoinpricetracker.BuildConfig;
import com.sipi1020.bitcoinpricetracker.ui.main.MainPresenter;
import com.sipi1020.bitcoinpricetracker.ui.main.MainScreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Date;

import static com.sipi1020.bitcoinpricetracker.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Viki on 2018-05-02.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainTest {

    private MainPresenter mainPresenter;
    private MainScreen mainScreen;

    @Before
    public void setup() throws Exception {
        setTestInjector();
        mainScreen = mock(MainScreen.class);
        mainPresenter = new MainPresenter();
        mainPresenter.attachScreen(mainScreen);
    }

    @Test
    public void testShowDates() {
        Date start = new Date();
        Date end = new Date();

        mainPresenter.showDefaultDates(start,end);
        verify(mainScreen).setDefaultDateValues(start,end);
    }

    @Test
    public void testRefreshPricesList(){
        
    }


    @After
    public void tearDown() {
        mainPresenter.detachScreen();
    }

}