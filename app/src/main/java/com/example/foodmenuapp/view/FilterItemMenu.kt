package com.example.foodmenuapp.view

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.example.foodmenuapp.model.DishDetails

@Composable
fun FilterMenu(mContext: Context, filters: List<DishDetails>) {
    var selectedDishPosition by remember { mutableStateOf<Int?>(null) }
    Text(
        text = "What's on your mind?"
        , fontSize = 15.sp
        , fontWeight = FontWeight.Bold
        ,textAlign = TextAlign.Center
        ,color =  Color.Blue
    )
    Spacer(modifier = Modifier.height(16.dp))
    LazyRow(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically) {
        items(filters, key = {item  -> item.dishId }){ dish ->
            var index = filters.indexOf(dish)
            val isSelected = selectedDishPosition == index
            FilterItemCard(item = dish
                ,isSelected = isSelected
                ,onClick = {
                    selectedDishPosition = if(isSelected) null else index
                  }
            )
        }
    }
}
@Composable
fun FilterItemCard(item: DishDetails, isSelected:Boolean, onClick:()->Unit) {
    val cardColor = if (isSelected) Color.Blue else Color.White
    val textColor = if (isSelected) Color.White else Color.Blue
    Card( modifier = Modifier
        .fillMaxWidth()
        .height(50.dp).padding(5.dp)
        .size(120.dp)
        .clickable { onClick() }, colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
            Row(
                modifier = Modifier
                    .padding(7.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = rememberImagePainter(item.imageUrl),
                    contentDescription = item.dishName,
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = item.dishName,
                     fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = textColor
                )

            }

    }

}