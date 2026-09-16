package com.example.listycity3

import androidx.compose.runtime.mutableStateListOf

class CityRepository {
    private val _cities = mutableStateListOf<City>(
        City("Edmonton", "AB"),
        City("Vancouver", "BC"),
        City("Toronto", "ON")
    )

    val cities: List<City>
        get() = _cities

    fun addCity(city: City){
        _cities.add(city)
    }

    fun editCityName(city:City,new_name:String){
        city.name = new_name
    }

    fun editCityProv(city:City, new_prov: String){
        city.province = new_prov
    }
}