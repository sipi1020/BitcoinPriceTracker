package com.sipi1020.bitcoinpricetracker.networking;

import com.sipi1020.bitcoinpricetracker.model.TimeRangeData;

import java.util.List;


import retrofit2.Call;
import retrofit2.http.*;


public interface FavoritesApi {
  
  /**
   * Returns the list of favorite time ranges of bitcoin prices
   * 
   * @return Call<List<TimeRangeData>>
   */
  
  @GET("favorites")
  Call<List<TimeRangeData>> getFavorites();
    

  
  /**
   * Add a favorite time range of bitcoin prices
   * 
   * @param body Timerange data
   * @return Call<Void>
   */
  
  @POST("favorites")
  Call<Void> addFavorite(
    @Body TimeRangeData body
  );

  
  /**
   * Removes the given time range data from favorites
   * 
   * @param favoriteId Favorite id to delete
   * @return Call<Void>
   */
  
  @DELETE("favorites/{favoriteId}")
  Call<Void> deleteFavorite(
    @Path("favoriteId") Long favoriteId
  );

  
}
