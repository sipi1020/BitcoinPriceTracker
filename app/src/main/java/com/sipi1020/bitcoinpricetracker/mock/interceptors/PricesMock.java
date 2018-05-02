package com.sipi1020.bitcoinpricetracker.mock.interceptors;

/**
 * Created by Viki on 2018-04-20.
 */

import android.net.Uri;

import com.google.gson.GsonBuilder;
import com.sipi1020.bitcoinpricetracker.model.PricesResult;
import com.sipi1020.bitcoinpricetracker.networking.NetworkConfig;

import java.util.HashMap;
import java.util.Map;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.sipi1020.bitcoinpricetracker.mock.interceptors.MockHelper.makeResponse;

public class PricesMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.BITCOIN_ENDPOINT_ADDRESS) && request.method().equals("Get")) {
            String start = uri.getQueryParameter("start");
            String end = uri.getQueryParameter("end");
            if (start == null || end == null){
                responseString = "";
                responseCode = 400;
            }
            else {
                Map<String, Double> prices = new HashMap<String, Double>();
                prices.put(start, new Double(5000));
                prices.put(end, new Double(6155));
                PricesResult result = new PricesResult();
                result.setPrices(prices);
                responseString = new GsonBuilder().create().toJson(result);
                responseCode = 200;
            }
        }
        else {
            responseString = "";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}