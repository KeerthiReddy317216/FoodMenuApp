package com.example.foodmenuapp.model.api

import com.example.foodmenuapp.model.DishDetails
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("nosh-assignment")
     fun getDishes(): Call<List<DishDetails>>
}