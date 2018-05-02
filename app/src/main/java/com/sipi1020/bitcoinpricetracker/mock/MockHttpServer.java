package com.sipi1020.bitcoinpricetracker.mock;

/**
 * Created by Viki on 2018-04-20.
 */

import com.sipi1020.bitcoinpricetracker.mock.interceptors.MockInterceptor;

import okhttp3.Request;
import okhttp3.Response;

public class MockHttpServer {

    public static Response call(Request request) {
        MockInterceptor mockInterceptor = new MockInterceptor();
        return mockInterceptor.process(request);
    }
}