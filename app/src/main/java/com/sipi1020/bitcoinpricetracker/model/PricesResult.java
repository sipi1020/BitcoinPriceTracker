package com.sipi1020.bitcoinpricetracker.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

    public List<PriceRecord> getPriceRecordList(){
        Object[] dateObjects = prices.keySet().toArray();
        int size = dateObjects.length;
        String [] dates = new String[size];
        for (int i = 0; i < size; i++)
            dates[i] = dateObjects[i].toString();
        Arrays.sort(dates);
        Collections.reverse(Arrays.asList(dates));

        List<PriceRecord> records = new ArrayList<>();
        for (String s: dates){
            PriceRecord record = new PriceRecord();
            record.price = prices.get(s);
            record.date = s;
            records.add(record);
        }
        return records;
    }
}
