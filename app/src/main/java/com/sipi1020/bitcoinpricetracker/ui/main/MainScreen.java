package com.sipi1020.bitcoinpricetracker.ui.main;

import com.sipi1020.bitcoinpricetracker.model.PriceRecord;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;

import java.util.Date;
import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */

public interface MainScreen {

    void reloadList(PricesResult records);
    void setDefaultDateValues(Date startDate, Date endDate);
    void setDateListeners();

}
