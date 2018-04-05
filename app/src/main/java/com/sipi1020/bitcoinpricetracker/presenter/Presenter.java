package com.sipi1020.bitcoinpricetracker.presenter;

/**
 * Created by Viki on 2018-04-05.
 */

public abstract class Presenter<S> {
    protected S screen;

    public void attachScreen(S screen) {
        this.screen = screen;
    }

    public void detachScreen() {
        this.screen = null;
    }
}