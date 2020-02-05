package shasin.weatherapp.repository

import androidx.lifecycle.LiveData
import shasin.weatherapp.data.responseModel.WeatherForecastResponse
import shasin.weatherapp.network.api.Resource

interface WeatherForecastRepository{
    fun getForecastWeather(query:String,days:Int): LiveData<Resource<WeatherForecastResponse>>
}