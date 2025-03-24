package com.example.foodmenuapp.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.foodmenuapp.R
@Composable
fun ScheduleCookingTime(onClose: () -> Unit, mContext: Context) {
    var selectedTime by remember { mutableStateOf("Select Time") }
    var onChangeTime by remember { mutableStateOf("AM") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier
                .size(300.dp)
                .padding(5.dp)
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Schedule cooking time",
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.Blue,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Image(
                    modifier = Modifier
                        .size(25.dp)
                        .clickable { onClose() },
                    painter = painterResource(id = R.drawable.close_icon),
                    contentDescription = "Close",
                    colorFilter = ColorFilter.tint(Color.Blue),
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Card(
                    modifier = Modifier
                        .size(170.dp)
                        .padding(10.dp),
                    colors = CardColors(
                        Color.LightGray,
                        Color.LightGray,
                        Color.LightGray,
                        Color.LightGray
                    ),
                ) {
                    TimePicker(onTimeSelected = { time ->
                        selectedTime = time
                    })
                }

                Column(
                    modifier = Modifier.padding(end = 40.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(
                        onClick = { onChangeTime = "AM"
                            Toast.makeText(mContext, "Selected AM", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = if(onChangeTime.equals("AM")){
                            Color.Blue
                        } else{Color.LightGray}),
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier./*size(width = 60.dp, height = 40.dp).*/padding(1.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(5.dp)
                    ) {
                        Text(text = "AM", color =
                        if(onChangeTime.equals("AM")){
                            Color(mContext.resources.getColor(R.color.white))
                        }
                        else{Color.Blue}
                            ,fontSize = 15.sp)
                    }

                    Button(
                        modifier = Modifier.padding(1.dp),
                        onClick = { onChangeTime = "PM"
                            Toast.makeText(mContext, "Selected PM", Toast.LENGTH_SHORT).show() },
                        colors = ButtonDefaults.buttonColors(containerColor = if(onChangeTime.equals("PM")){
                            Color.Blue
                        } else{Color.LightGray}),
                        shape = RoundedCornerShape(2.dp),
                        elevation = ButtonDefaults.elevatedButtonElevation(5.dp)
                    ) {
                        Text(text = "PM", color =   if(onChangeTime.equals("PM")){
                            Color(mContext.resources.getColor(R.color.white))
                        }
                        else{Color.Blue}, fontSize = 15.sp)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 20.dp, end = 2.dp)
                        .clickable {
                            onClose()
                            Toast
                                .makeText(mContext, "Successfully Deleted", Toast.LENGTH_SHORT)
                                .show()
                        },
                    text = "Delete",
                    fontWeight = FontWeight.Normal,
                    color = Color.Red,
                    style = TextStyle(
                        textDecoration = TextDecoration.Underline
                    )
                )

                Button(
                    modifier = Modifier.padding(2.dp),
                    border = BorderStroke(1.dp, Color(mContext.resources.getColor(R.color.menu_selected_color))),
                    onClick = { onClose()
                        Toast.makeText(mContext, "Successfully Rescheduled the time", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(mContext.resources.getColor(R.color.white)))
                ) {
                    Text(text = "Re-schedule", color = Color(mContext.resources.getColor(R.color.menu_selected_color)))
                }

                Button(
                    modifier = Modifier.padding(2.dp),
                    border = BorderStroke(1.dp, Color(mContext.resources.getColor(R.color.menu_selected_color))),
                    onClick = { onClose()
                        Toast.makeText(mContext, "Successfully Started Cooking", Toast.LENGTH_SHORT).show() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(mContext.resources.getColor(R.color.menu_selected_color)))
                ) {
                    Text(text = "Cook now", color = Color(mContext.resources.getColor(R.color.white)))
                }
            }
        }
    }
}

@Composable
fun TimePicker(onTimeSelected: (String) -> Unit) {
    val hours = List(24) { it }
    val minutes = List(60) { it }

    // Mutable state to hold the selected hour and minute
    var selectedHour by remember { mutableStateOf(0) }
    var selectedMinute by remember { mutableStateOf(0) }

    // Lazy list state to track the scroll position
    val hourState = rememberLazyListState()
    val minuteState = rememberLazyListState()

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Hour Picker
        LazyColumn(
            state = hourState,
            modifier = Modifier
                .weight(1f)
                .height(120.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 1.dp)
        ) {
            itemsIndexed(hours) { index, hour ->
                val isSelected = hour == selectedHour
                Text(
                    text = hour.toString().padStart(2, '0'),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable {
                            selectedHour = hour
                            onTimeSelected(
                                "${
                                    selectedHour
                                        .toString()
                                        .padStart(2, '0')
                                }:${
                                    selectedMinute
                                        .toString()
                                        .padStart(2, '0')
                                }"
                            )
                        }
                        .padding(1.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color = Color.Blue ,
                    textAlign = TextAlign.Center
                )
            }
        }

        // Colon separator
        Text(
            text = ":",
            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(top = 40.dp),
            textAlign = TextAlign.Center,
            color = Color.Blue,

        )

        // Minute Picker
        LazyColumn(
            state = minuteState,
            modifier = Modifier
                .weight(1f)
                .height(120.dp)
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.Center,
            contentPadding = PaddingValues(vertical = 1.dp)
        ) {
            itemsIndexed(minutes) { index, minute ->
                val isSelected = minute == selectedMinute
                Text(
                    text = minute.toString().padStart(2, '0'),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                        .clickable {
                            selectedMinute = minute
                            onTimeSelected(
                                "${
                                    selectedHour
                                        .toString()
                                        .padStart(2, '0')
                                }:${
                                    selectedMinute
                                        .toString()
                                        .padStart(2, '0')
                                }"
                            )
                        }
                        .padding(1.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    color =  Color.Blue,
                    textAlign = TextAlign.Center
                )
            }
        }
    }

    LaunchedEffect(selectedHour, selectedMinute) {
        hourState.scrollToItem(hours.indexOf(selectedHour))
        minuteState.scrollToItem(minutes.indexOf(selectedMinute))
    }
}



