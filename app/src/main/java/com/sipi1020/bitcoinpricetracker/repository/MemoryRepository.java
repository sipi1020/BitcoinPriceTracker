package com.sipi1020.bitcoinpricetracker.repository;

import android.content.Context;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Viki on 2018-04-20.
 */

public class MemoryRepository implements Repository {

    private static final long MINUTE = 60 * 1000;

    public static List<TimeRangeData> data;

    @Override
    public void open(Context context) {
        TimeRangeData data1 = new TimeRangeData(new Long(1),"2018-03-15",
                "2018-03-22",null);
        TimeRangeData data2 = new TimeRangeData(new Long(2),"2018-03-17",
                "2018-03-26",null);
        data = new ArrayList<>();
        data.add(data1);
        data.add(data2);
    }

    @Override
    public List<TimeRangeData> getFavorites() {
        return null;
    }

    @Override
    public void saveFavorite(TimeRangeData data) {
        this.data.add(data);
    }

    @Override
    public void removeFavorite(long id) {
        TimeRangeData item = null;
        for (TimeRangeData t : data){
            if (t.getId() == id){
                item = t;
            }
        }
        if (item != null){
            data.remove(item);
        }
    }

    @Override
    public void close() {

    }


}