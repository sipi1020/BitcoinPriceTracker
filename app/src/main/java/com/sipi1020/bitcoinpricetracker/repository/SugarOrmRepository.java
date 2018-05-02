package com.sipi1020.bitcoinpricetracker.repository;

/**
 * Created by Viki on 2018-04-20.
 */

import android.content.Context;

import com.orm.SugarContext;
import com.orm.SugarRecord;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.ArrayList;
import java.util.List;


public class SugarOrmRepository implements Repository {

    @Override
    public void open(Context context) {
        SugarContext.init(context);
    }

    @Override
    public void close() {
        SugarContext.terminate();
    }

    @Override
    public List<TimeRangeData> getFavorites() {
        return SugarRecord.listAll(TimeRangeData.class);
    }

    @Override
    public void saveFavorite(TimeRangeData data) {
        SugarRecord.saveInTx(data);
    }

    @Override
    public void removeFavorite(long id) {
        SugarRecord.deleteInTx(SugarRecord.findById(TimeRangeData.class,id));
    }

    @Override
    public void removeFavorite(TimeRangeData data) {
        SugarRecord.deleteInTx(data);
    }
}