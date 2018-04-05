package com.sipi1020.bitcoinpricetracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

/**
 * Created by Viki on 2018-04-05.
 */

public class PricesResult {

    @SerializedName("bpi")
    @Expose
    private Map<String,Double> prices;

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Double> prices) {
        this.prices = prices;
    }
}
