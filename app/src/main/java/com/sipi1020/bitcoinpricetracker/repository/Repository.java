package com.sipi1020.bitcoinpricetracker.repository;

import android.content.Context;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
import com.sipi1020.bitcoinpricetracker.networking.FavoritesApi;

import java.util.List;

/**
 * Created by Viki on 2018-04-20.
 */

public interface Repository {

    void open(Context context);

    void close();

    List<TimeRangeData> getFavorites();

    void saveFavorite(TimeRangeData data);

    void removeFavorite(long id);

    void removeFavorite(TimeRangeData data);
}
