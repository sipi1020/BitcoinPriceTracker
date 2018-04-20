package com.sipi1020.bitcoinpricetracker.iteractor.events;

import com.sipi1020.bitcoinpricetracker.model.PricesResult;

/**
 * Created by Viki on 2018-04-20.
 */

public class GetPricesEvent {
    private int code;
    private PricesResult prices;
    private Throwable throwable;

    public GetPricesEvent() {
    }

    public GetPricesEvent(int code, PricesResult prices, Throwable throwable) {
        this.code = code;
        this.prices = prices;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PricesResult getPrices() {
        return prices;
    }

    public void setPrices(PricesResult prices) {
        this.prices = prices;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
