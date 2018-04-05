package com.sipi1020.bitcoinpricetracker.view.screen;

import com.sipi1020.bitcoinpricetracker.model.PriceRecord;

import java.util.Date;
import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */

public interface MainScreen {

    void reloadList(List<PriceRecord> records);
    void setDefaultDateValues(Date startDate, Date endDate);

}
