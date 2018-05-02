package com.sipi1020.bitcoinpricetracker.networking;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Viki on 2018-04-05.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public Retrofit.Builder provideRetrofit() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create());

    }

    @Provides
    @Singleton
    public Retrofit provideRetrofitFromClient(OkHttpClient client) {
        return new Retrofit.Builder().baseUrl(NetworkConfig.SERVER_ENDPOINT_ADDRESS).client(client)
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClientBuilder() {
        return new OkHttpClient().newBuilder();
    }


    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        return builder.build();
    }

    @Provides
    @Singleton
    public PricesApi providePricesApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(NetworkConfig.BITCOIN_ENDPOINT_ADDRESS).build().create(PricesApi.class);
    }

    @Provides
    @Singleton
    public FavoritesApi provideFavoritesApi(Retrofit.Builder retrofitBuilder) {
        return retrofitBuilder.baseUrl(NetworkConfig.SERVER_ENDPOINT_ADDRESS).build().create(FavoritesApi.class);
    }
}
