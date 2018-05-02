package com.sipi1020.bitcoinpricetracker.model;

import com.google.gson.annotations.Expose;
import com.orm.dsl.Table;

/**
 * Created by Viki on 2018-04-05.
 */

@Table
public class PriceRecord {

    Long id;
    @Expose
    String date;
    @Expose
    Double price;

}
