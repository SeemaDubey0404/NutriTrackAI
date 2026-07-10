package com.nutrition.nutritrackai.data.remote.api



import retrofit2.http.GET
import retrofit2.http.Query


import com.nutrition.nutritrackai.data.remote.dto.FoodSearchResponse


interface FoodApi {

    @GET("cgi/search.pl")
    suspend fun searchFood(

        @Query("search_terms")
        query: String,

        @Query("search_simple")
        simple: Int = 1,

        @Query("action")
        action: String = "process",

        @Query("json")
        json: Int = 1

    ): FoodSearchResponse

}