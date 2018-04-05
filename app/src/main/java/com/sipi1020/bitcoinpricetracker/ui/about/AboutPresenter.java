package com.sipi1020.bitcoinpricetracker.ui.about;

import com.sipi1020.bitcoinpricetracker.ui.Presenter;

/**
 * Created by Viki on 2018-04-05.
 */

public class AboutPresenter extends Presenter<AboutScreen> {

    @Override
    public void attachScreen(AboutScreen screen) {
        super.attachScreen(screen);
    }

    @Override
    public void detachScreen() {
        super.detachScreen();
    }

    public void showDeveloperWebsite(String url){
        screen.openDeveloperSite(url);
    }

}
