package com.sipi1020.bitcoinpricetracker.iteractor.events;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

/**
 * Created by Viki on 2018-04-20.
 */

public class SaveFavoriteEvent {

    private int code;
    private TimeRangeData data;
    private Throwable throwable;

    public SaveFavoriteEvent() {
    }

    public SaveFavoriteEvent(int code, TimeRangeData data, Throwable throwable) {
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

    public TimeRangeData getData() {
        return data;
    }

    public void setData(TimeRangeData data) {
        this.data = data;
    }

    public Throwable getThrowable() {
        return throwable;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
