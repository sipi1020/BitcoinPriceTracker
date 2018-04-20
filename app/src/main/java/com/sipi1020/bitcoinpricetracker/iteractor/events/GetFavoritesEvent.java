package com.sipi1020.bitcoinpricetracker.iteractor.events;

import com.sipi1020.bitcoinpricetracker.model.PricesResult;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.List;

/**
 * Created by Viki on 2018-04-20.
 */

public class GetFavoritesEvent {

    private int code;
    private List<TimeRangeData> data;
    private Throwable throwable;

    public GetFavoritesEvent() {
    }

    public GetFavoritesEvent(int code, List<TimeRangeData> data, Throwable throwable) {
        this.code = code;
        this.data = data;
        this.throwable = throwable;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<TimeRangeData> getData() {
        return data;
    }

    public void setData(List<TimeRangeData> data) {
        this.data = data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
