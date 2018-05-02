package com.sipi1020.bitcoinpricetracker.mock.interceptors;

/**
 * Created by Viki on 2018-04-20.
 */

import android.net.Uri;

import com.sipi1020.bitcoinpricetracker.networking.NetworkConfig;

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
            responseString = "";
            responseCode = 200;
        }
        else {
            responseString = "ERROR";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}