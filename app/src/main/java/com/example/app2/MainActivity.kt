package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.app2.databinding.ActivityMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject




class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btGetWeatherReport.setOnClickListener {
            getWeather()
        }


    }

    private fun getWeather() {

        val city = binding.etSearchCity.text.toString()

        val weather = WeatherService.weatherInstance.getCurrentWeather("$city", "no")
        weather.enqueue(object : Callback<GetWeather?> {
            override fun onResponse(call: Call<GetWeather?>, response: Response<GetWeather?>) {

                if(response.body() == null)
                    Toast.makeText(this@MainActivity , "enter valid city name" , Toast.LENGTH_SHORT).show()

                else {

                    binding.tvCloud.text = response.body()?.current?.cloud.toString()
                    binding.tvFeelsLikeC.text = response.body()?.current?.feelslike_c.toString()
                    binding.tvFeelsLikeF.text = response.body()?.current?.feelslike_f.toString()
                    binding.tvWindDir.text = response.body()?.current?.wind_dir.toString()
                    binding.tvWindDegree.text = response.body()?.current?.wind_degree.toString()
                    binding.tvTempF.text = response.body()?.current?.temp_f.toString()
                    binding.tvTempC.text = response.body()?.current?.temp_c.toString()
                    binding.tvPressureMb.text = response.body()?.current?.pressure_mb.toString()
                    binding.tvPressureIn.text = response.body()?.current?.pressure_in.toString()
                    binding.tvPrecipMm.text = response.body()?.current?.precip_mm.toString()
                    binding.tvPrecipIn.text = response.body()?.current?.precip_in.toString()
                    binding.tvIsDay.text = response.body()?.current?.is_day.toString()
                    binding.tvHumidity.text = response.body()?.current?.humidity.toString()
                    binding.tvUv.text = response.body()?.current?.uv.toString()
                    binding.tvWindKph.text = response.body()?.current?.wind_kph.toString()
                }
            }

            override fun onFailure(call: Call<GetWeather?>, t: Throwable) {
            }
        })
    }
}