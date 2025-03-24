package com.example.foodmenuapp.view
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopSearchBar() {
    var searchQuery = remember { mutableStateOf("") }
    var active = remember { mutableStateOf(false) }
    SearchBar(
        query = searchQuery.value,
        onQueryChange = { newValue->searchQuery.value = newValue  },
        onSearch = { active.value = false },
        active = active.value,
        onActiveChange = {finalValue->active.value = finalValue},

        modifier = Modifier
            .padding(start = 12.dp, top = 2.dp, end = 12.dp, bottom = 12.dp)
            .fillMaxWidth() ,

        placeholder = { Text("Search") },

        leadingIcon = {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        },
        trailingIcon = {
            if (active.value)
                Icon(
                    imageVector = Icons.Rounded.Close,
                    contentDescription = null
                )
        },
        colors = SearchBarDefaults.colors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
        ),
        tonalElevation = 0.dp,
    ) {
    }
}