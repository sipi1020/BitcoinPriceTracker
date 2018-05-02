package com.sipi1020.bitcoinpricetracker.model;

import com.google.gson.annotations.Expose;
import com.orm.dsl.Table;

/**
 * Created by Viki on 2018-04-05.
 */

@Table
public class PriceRecord {

    @Expose
    Long id;
    @Expose
    String date;
    @Expose
    Double price;

    public PriceRecord() {
    }

    public PriceRecord(Long id, String date, Double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
