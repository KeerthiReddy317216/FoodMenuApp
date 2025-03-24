package com.example.foodmenuapp.model.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
   var BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/dev/"

    fun getInstance():Retrofit {
        return Retrofit.Builder().
        baseUrl(BASE_URL).
        addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}