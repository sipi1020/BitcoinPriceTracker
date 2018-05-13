package com.sipi1020.bitcoinpricetracker.mock.interceptors;

/**
 * Created by Viki on 2018-04-20.
 */

import android.net.Uri;
import android.util.Log;

import com.sipi1020.bitcoinpricetracker.networking.NetworkConfig;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.sipi1020.bitcoinpricetracker.mock.interceptors.MockHelper.makeResponse;


public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        return process(chain.request());
    }

    public Response process(Request request) {

        Uri uri = Uri.parse(request.url().toString());

        Log.d("Test Http Client", "URL call: " + uri.toString());
        Headers headers = request.headers();


        if (uri.toString().startsWith(NetworkConfig.SERVER_ENDPOINT_ADDRESS)) {
            return FavoritesMock.process(request);
        }

        if (uri.toString().startsWith(NetworkConfig.BITCOIN_ENDPOINT_ADDRESS)) {
            return PricesMock.process(request);
        }

        return makeResponse(request, headers, 404, "Unknown");

    }

}