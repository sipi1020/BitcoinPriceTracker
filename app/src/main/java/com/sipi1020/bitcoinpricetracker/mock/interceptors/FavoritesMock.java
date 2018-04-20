package com.sipi1020.bitcoinpricetracker.mock.interceptors;

/**
 * Created by Viki on 2018-04-20.
 */

import android.net.Uri;

import com.sipi1020.bitcoinpricetracker.networking.NetworkConfig;
import com.sipi1020.bitcoinpricetracker.repository.MemoryRepository;

import okhttp3.Headers;
import okhttp3.Request;
import okhttp3.Response;

import static com.sipi1020.bitcoinpricetracker.mock.interceptors.MockHelper.makeResponse;

public class FavoritesMock {
    public static Response process(Request request) {
        Uri uri = Uri.parse(request.url().toString());

        String responseString;
        int responseCode;
        Headers headers = request.headers();


        if (uri.getPath().equals(NetworkConfig.SERVER_ENDPOINT_ADDRESS + "favorites") && request.method().equals("Get")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites());

            responseString = "";
            responseCode = 200;
        }
        else if (uri.getPath().equals(NetworkConfig.SERVER_ENDPOINT_ADDRESS + "favorites") && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites());

            responseString = "";
            responseCode = 200;
        }
        else if (uri.getPath().equals(NetworkConfig.SERVER_ENDPOINT_ADDRESS + "favorites/") && request.method().equals("DELETE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            //responseString = GsonHelper.getGson().toJson(memoryRepository.getFavourites());

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