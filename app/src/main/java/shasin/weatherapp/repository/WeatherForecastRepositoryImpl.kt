package shasin.weatherapp.repository

import androidx.lifecycle.LiveData
import shasin.weatherapp.data.responseModel.WeatherForecastResponse
import shasin.weatherapp.network.api.APIService
import shasin.weatherapp.network.api.ApiRoutes
import shasin.weatherapp.network.api.NetworkCall
import shasin.weatherapp.network.api.Resource
import javax.inject.Inject

class WeatherForecastRepositoryImpl @Inject constructor(var apiService: APIService):WeatherForecastRepository{
    private val foreCastWeatherCall = NetworkCall<WeatherForecastResponse>()

    override fun getForecastWeather(query: String, days: Int):LiveData<Resource<WeatherForecastResponse>> {
       return foreCastWeatherCall.makeCall(apiService.getForecastWeather(ApiRoutes.API_KEY,query,days))
    }
}