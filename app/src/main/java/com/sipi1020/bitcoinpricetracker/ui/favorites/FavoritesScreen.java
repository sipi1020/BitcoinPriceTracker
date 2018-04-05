package com.sipi1020.bitcoinpricetracker.ui.favorites;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */

public interface FavoritesScreen{

    void reloadData(List<TimeRangeData> data);

}
