package com.sipi1020.bitcoinpricetracker.networking;

import com.sipi1020.bitcoinpricetracker.model.PricesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Viki on 2018-04-05.
 */

public interface PricesApi {

    @GET("historical/close.json")
    Call<PricesResult> getPrices(@Query("start") String start, @Query("end") String end);

}
