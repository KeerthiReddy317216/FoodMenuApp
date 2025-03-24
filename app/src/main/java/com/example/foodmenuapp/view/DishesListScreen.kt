package com.example.foodmenuapp.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.foodmenuapp.model.DishDetails
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
    fun ItemsListScreen(mContext: Context, dishes: List<DishDetails>) {
        var showScheduleCookingTime = remember { mutableStateOf(false) }
        var selectedDishPosition by remember { mutableStateOf<Int?>(null) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
    ) {
        TopSearchBar()
        Spacer(modifier = Modifier.height(16.dp))
        FilterMenu(mContext = mContext, filters = dishes )
        Spacer(modifier = Modifier.height(16.dp))
        if (showScheduleCookingTime.value) {
            Dialog(onDismissRequest = { showScheduleCookingTime.value = false}) {
                ScheduleCookingTime(onClose = {showScheduleCookingTime.value = false},mContext)
            }
        }
        LazyRow(modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically) {
            items(dishes, key = {item  -> item.dishId }){ dish ->
                var index = dishes.indexOf(dish)
                val isSelected = selectedDishPosition == index
                ItemCard(item = dish
                    ,isSelected = isSelected
                    ,onClick = {
                        selectedDishPosition = if(isSelected) null else index
                        showScheduleCookingTime.value = true}
                )
            }
        }
    }
    }
    @Composable
    fun ItemCard(item: DishDetails,isSelected:Boolean,onClick:()->Unit) {
        val cardColor = if (isSelected) Color.Blue else Color.White
        val textColor = if (isSelected) Color.White else Color.Blue
        Card( modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .height(280.dp)
            .clickable { onClick() }, colors = CardDefaults.cardColors(containerColor = cardColor),
            elevation = CardDefaults.cardElevation(5.dp)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.LightGray)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = rememberImagePainter(item.imageUrl),
                        contentDescription = item.dishName,
                        modifier = Modifier
                            .size(150.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                    )

                }
            }
            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)
                , text = item.dishId, fontWeight = FontWeight.Bold
                , textAlign = TextAlign.Center,
                color = textColor
            )

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp)
                ,text = item.dishName
                , fontWeight = FontWeight.Bold
                ,textAlign = TextAlign.Center
                ,color = textColor)

            Text(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(2.dp)
                ,text = "30 min . Medium prep."
                , fontSize = 10.sp
                , fontWeight = FontWeight.Normal
                ,textAlign = TextAlign.Center
                ,color = if (isSelected) Color.Gray else Color.Black)
        }

    }

