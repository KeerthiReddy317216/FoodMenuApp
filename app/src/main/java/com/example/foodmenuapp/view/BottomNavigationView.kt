package com.example.foodmenuapp.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodmenuapp.R
import com.example.foodmenuapp.utils.Constants
import androidx.compose.runtime.livedata.observeAsState
import com.example.foodmenuapp.viewmodel.DishViewModel

@Composable
fun NavHostContainer(
    context: Context,
    navController: NavHostController,
    padding: PaddingValues,
    viewModel: DishViewModel
) {
    val dishes by viewModel.getAllDishes().observeAsState(initial = emptyList())
    NavHost(
        navController = navController,

        startDestination = "cook",

        modifier = Modifier.padding(paddingValues = padding),

        builder = {
            composable("cook") {
                ItemsListScreen(context,dishes = dishes)
            }

            composable("favourites") {
                ItemsListScreen(context,dishes = dishes)
            }

            composable("settings") {
                ItemsListScreen(context,dishes = dishes)

            }
            composable("manual") {
                ItemsListScreen(context,dishes = dishes)
            }
        })
}
@Composable
fun BottomNavigationBar(mContext:Context,navController: NavHostController) {
    var currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var selectedItem by remember { mutableStateOf(0) }
    NavigationBar(
        containerColor = Color.LightGray

    ) {
        NavigationBarItem(
            selected = currentRoute == Constants.COOK_ROUTE,
            onClick = {
                selectedItem = 0
                navController.navigate(Constants.COOK_ROUTE)
            },
            icon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.cooking_icon),
                    contentDescription = "Cook",
                    colorFilter = if (currentRoute == Constants.COOK_ROUTE) {
                        ColorFilter.tint(Color(mContext.resources.getColor(R.color.menu_selected_color)))
                    } else {
                        ColorFilter.tint(Color.Blue)
                    }
                )
            },
            label = { Text(text = "Cook") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(mContext.resources.getColor(R.color.menu_selected_color)),
                unselectedIconColor = Color.Blue,
                selectedTextColor = Color(mContext.resources.getColor(R.color.menu_selected_color)),
                unselectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == Constants.FAVOURITES_ROUTE,
            onClick = {
                selectedItem = 1
                currentRoute = Constants.FAVOURITES_ROUTE
                navController.navigate(Constants.FAVOURITES_ROUTE)
            },
            icon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.favourite_icon),
                    contentDescription = "Favourites",
                    colorFilter = if (currentRoute == Constants.FAVOURITES_ROUTE) {
                        ColorFilter.tint(Color(mContext.resources.getColor(R.color.menu_selected_color)))
                    } else {
                        ColorFilter.tint(Color.Blue)
                    }
                )
            },
            label = { Text(text = "Favourites") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(mContext.resources.getColor(R.color.menu_selected_color)),
                unselectedIconColor = Color.Blue,
                selectedTextColor = Color(mContext.getColor(R.color.menu_selected_color)),
                unselectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == Constants.MANUAL_ROUTE,
            onClick = {
                selectedItem = 2
                currentRoute = Constants.MANUAL_ROUTE
                navController.navigate(Constants.MANUAL_ROUTE)
            },
            icon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.manual_icon),
                    contentDescription = "Manual",
                    colorFilter = if (currentRoute == Constants.MANUAL_ROUTE) {
                        ColorFilter.tint(Color(mContext.getColor(R.color.menu_selected_color)))
                    } else {
                        ColorFilter.tint(Color.Blue)
                    }
                )
            },
            label = { Text(text = "Manual") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(mContext.getColor(R.color.menu_selected_color)),
                unselectedIconColor = Color.Blue,
                selectedTextColor = Color(mContext.getColor(R.color.menu_selected_color)),
                unselectedTextColor = Color.Blue,
                indicatorColor = Color.Transparent
            )
        )
        NavigationBarItem(
            selected = currentRoute == Constants.SETTINGS_ROUTE,
            onClick = {
                selectedItem = 3
                currentRoute = Constants.SETTINGS_ROUTE
                navController.navigate(Constants.SETTINGS_ROUTE)
            },
            icon = {
                Image(
                    modifier = Modifier.size(25.dp),
                    painter = painterResource(id = R.drawable.settings_icon),
                    contentDescription = "Settings",
                    colorFilter = if (currentRoute == Constants.SETTINGS_ROUTE) {
                        ColorFilter.tint(Color(mContext.resources.getColor(R.color.menu_selected_color)))
                    } else {
                        ColorFilter.tint(Color.Blue)
                    }
                )
            },
            label = { Text(text = "Settings") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor =  Color(mContext.getColor(R.color.menu_selected_color)),
                unselectedIconColor = Color.Blue,
                selectedTextColor =  Color(mContext.getColor(R.color.menu_selected_color)),
                unselectedTextColor =  Color.Blue,
                indicatorColor = Color.Transparent
            )
        )
    }
}