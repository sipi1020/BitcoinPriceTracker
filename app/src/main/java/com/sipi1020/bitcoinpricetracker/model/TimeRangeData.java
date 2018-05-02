package com.sipi1020.bitcoinpricetracker.model;

import com.google.gson.annotations.Expose;
import com.orm.dsl.Table;

import java.util.List;

/**
 * Created by Viki on 2018-04-05.
 */
@Table
public class TimeRangeData {

    Long id = null;
    @Expose
    String startDate;
    @Expose
    String endDate;
    @Expose
    List<PriceRecord> prices;

    public TimeRangeData() {
    }

    public TimeRangeData(Long id, String startDate, String endDate, List<PriceRecord> prices) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.prices = prices;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<PriceRecord> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceRecord> prices) {
        this.prices = prices;
    }
}
