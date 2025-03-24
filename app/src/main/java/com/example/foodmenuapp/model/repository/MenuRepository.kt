package com.example.foodmenuapp.model.repository
import androidx.lifecycle.MutableLiveData
import com.example.foodmenuapp.model.DishDetails
import com.example.foodmenuapp.model.api.ApiService
import com.example.foodmenuapp.model.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


object MenuRepository {
   lateinit var  api :ApiService
    var dishes : MutableLiveData<List<DishDetails>> = MutableLiveData()

   fun getApiService():ApiService{
        api =  RetrofitInstance.getInstance().create(ApiService::class.java)
       return api
    }

    fun getAllDishes():MutableLiveData<List<DishDetails>>{
        val call = getApiService()
        call.getDishes().enqueue(object : Callback<List<DishDetails>>{
            override fun onResponse(
                call: Call<List<DishDetails>>,
                response: Response<List<DishDetails>>
            ) {
                if(response.isSuccessful && response.body() != null){
                    dishes.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<List<DishDetails>>, t: Throwable) {
               dishes.postValue(emptyList())
            }

        })
        return dishes
    }

}