package com.sipi1020.bitcoinpricetracker.mock;

/**
 * Created by Viki on 2018-04-20.
 */

import com.sipi1020.bitcoinpricetracker.networking.FavoritesApi;
import com.sipi1020.bitcoinpricetracker.networking.NetworkModule;
import com.sipi1020.bitcoinpricetracker.networking.PricesApi;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

@Module
public class MockNetworkModule {
    private NetworkModule networkModule = new NetworkModule();

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return networkModule.provideOkHttpClientBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {

        builder.interceptors().add(3, new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                return MockHttpServer.call(request);
            }
        });

        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(OkHttpClient client) {
        return networkModule.provideRetrofitFromClient(client);
    }

    @Provides
    @Singleton
    public FavoritesApi provideFavoritesApi(Retrofit retrofit) {
        return networkModule.provideFavoritesApi(retrofit.newBuilder());
    }

    @Provides
    @Singleton
    public PricesApi providePricesApi(Retrofit retrofit) {
        return networkModule.providePricesApi(retrofit.newBuilder());
    }



}
