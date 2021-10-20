package com.example.app2

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL = "https://api.weatherapi.com/v1/"
const val API_KEY = "52ae76e2d5874f4e913175437210310"

interface WeatherInterface {

    @GET("current.json?key=$API_KEY")
    fun getCurrentWeather (@Query("q") city:String ,@Query("aqi") aqi: String) : Call<GetWeather>
}

//https://api.weatherapi.com/v1/current.json?key=52ae76e2d5874f4e913175437210310&q=London&aqi=no
object WeatherService {
    val weatherInstance : WeatherInterface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        weatherInstance = retrofit.create(WeatherInterface::class.java)
    }
}