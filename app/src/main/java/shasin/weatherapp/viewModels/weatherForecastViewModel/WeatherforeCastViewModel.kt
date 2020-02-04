package shasin.weatherapp.viewModels.weatherForecastViewModel

import android.arch.lifecycle.LiveData
import shasin.weatherapp.data.model.ForecastDays
import shasin.weatherapp.data.responseModel.WeatherForecastResponse
import shasin.weatherapp.network.api.Resource
import shasin.weatherapp.network.api.ResourceError

interface WeatherforeCastViewModel{
    fun isLoading():LiveData<Boolean>
    fun getError():LiveData<ResourceError>
    fun getCurrentWeather():LiveData<String>
    fun getCurrentLocationName():LiveData<String>
    fun getForeCastWeathers():LiveData<ForecastDays>
    fun callForecastWeatherAPI(query:String,days:Int)
    fun processResponse(response: Resource<WeatherForecastResponse>?)
}