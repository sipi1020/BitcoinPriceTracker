package com.sipi1020.bitcoinpricetracker.mock.interceptors;

/**
 * Created by Viki on 2018-04-20.
 */

import android.net.Uri;

import com.google.gson.GsonBuilder;
import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;
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
            responseString = new GsonBuilder().create().toJson(memoryRepository.getFavorites());
            responseCode = 200;
        }
        else if (uri.getPath().equals(NetworkConfig.SERVER_ENDPOINT_ADDRESS + "favorites") && request.method().equals("POST")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            String body = request.body().toString();
            TimeRangeData data = new GsonBuilder().create().fromJson(body, TimeRangeData.class);
            memoryRepository.saveFavorite(data);
            responseString = "";
            responseCode = 200;
        }
        else if (uri.getPath().equals(NetworkConfig.SERVER_ENDPOINT_ADDRESS + "favorites/") && request.method().equals("DELETE")) {
            MemoryRepository memoryRepository = new MemoryRepository();
            memoryRepository.open(null);
            String id = uri.getPathSegments().get(uri.getPathSegments().size()-1);
            memoryRepository.removeFavorite(Long.parseLong(id));

            responseString = "";
            responseCode = 200;
        }
        else {
            responseString = "";
            responseCode = 503;
        }

        return makeResponse(request, headers, responseCode, responseString);
    }
}