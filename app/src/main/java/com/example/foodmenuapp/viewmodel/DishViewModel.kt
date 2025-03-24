package com.example.foodmenuapp.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodmenuapp.model.DishDetails
import com.example.foodmenuapp.model.repository.MenuRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DishViewModel : ViewModel() {
     var dishList:MutableLiveData<List<DishDetails>> = MutableLiveData()
     fun getAllDishes():MutableLiveData<List<DishDetails>>{
        dishList = MenuRepository.getAllDishes()
        return dishList
    }
}