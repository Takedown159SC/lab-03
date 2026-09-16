package com.example.listycity3


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listycity3.ui.theme.ListyCity3Theme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.FloatingActionButton
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.graphics.Color


@Composable
fun CityListScreen(
    cities: List<City>,
    onAddCity: (City) -> Unit,
    modifier: Modifier = Modifier
) {
    var newCityName by remember {mutableStateOf("")}
    var newProvinceName by remember{mutableStateOf("")}
    var showAddCityFields by remember{mutableStateOf(false)}

    Column(modifier = modifier.fillMaxSize())  {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            FloatingActionButton(
                modifier = Modifier.padding(16.dp),
                onClick = {
                    showAddCityFields = !showAddCityFields
                }
            ) {
                Text("+")
            }
        }

        //only show when button is toggled
        if (showAddCityFields) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {   //City Text field
                OutlinedTextField(
                    value = newCityName,
                    onValueChange = { newCityName = it },
                    label = { Text("City") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))

                //Province Text Field
                OutlinedTextField(
                    value = newProvinceName,
                    onValueChange = { newProvinceName = it },
                    label = { Text("Province") },
                    modifier = Modifier.weight(1f)
                )

                //Add City button
                Button(
                    modifier = Modifier
                        .padding(vertical = 12.dp),
                    onClick = {
                        if ((newCityName.isNotBlank()) && newProvinceName.isNotBlank()) {
                            onAddCity(
                                City(
                                    name = newProvinceName,
                                    province = newProvinceName
                                )
                            )

                        }
                        //reset field text if user leaves one blank
                        newCityName = ""
                        newProvinceName = ""
                        showAddCityFields = false //stop showing field text if user being dumb

                    }
                )
                {
                    Text("Add City")

                }

            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize())
        {
            itemsIndexed(cities)
            { index, city ->
                CityRow(city = city)

                if (index < cities.lastIndex) {
                    HorizontalDivider()
                }
            }
            }

    }
}

@Composable
fun CityRow(city: City) {
    var newCityName by remember {mutableStateOf("")}
    var newProvinceName by remember{mutableStateOf("")}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        TextField(
            value = newCityName,
            onValueChange = { newCityName = it },
            label = { Text(city.name, fontSize = 20.sp,) },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
                    )
        )

        TextField(
            value = newProvinceName,
            onValueChange = { newProvinceName = it },
            label = { Text(city.province, fontSize = 20.sp,) },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent
            )
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CityListScreenPreview() {
    ListyCity3Theme {
        CityListScreen(
            cities = listOf(
                City("Edmonton", "AB"),
                City("Vancouver", "BC"),
                City("Calgary", "AB")
            ),
                onAddCity = {}
        )
    }
}


//FOCUS AND KEYBPOARD INPUTET REF: https://www.youtube.com/watch?v=CHtso6Cwi94
//https://developer.android.com/develop/ui/compose/touch-input/focus
//https://developer.android.com/reference/kotlin/androidx/compose/ui/input/key/onKeyEvent.modifier
//https://kotlinlang.org/api/compose-multiplatform/material3/androidx.compose.material3/-text-field-defaults/