package com.example.foodmenuapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.foodmenuapp.ui.theme.FoodMenuAppTheme
import com.example.foodmenuapp.view.BottomNavigationBar
import com.example.foodmenuapp.view.NavHostContainer
import com.example.foodmenuapp.viewmodel.DishViewModel
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<DishViewModel>()
            setContent {
                FoodMenuAppTheme(dynamicColor = false, darkTheme = false) {
                    var navController = rememberNavController()
                    Surface(color = Color.White) {
                        // Scaffold Component
                        Scaffold(
                            // Bottom navigation
                            bottomBar = {
                                BottomNavigationBar(
                                        this,
                                        navController = navController)
                            }, content = { padding ->
                                NavHostContainer(this,navController = navController, padding = padding,viewModel)
                            }
                        )
                    }
                }
            }
    }
}
