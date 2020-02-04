package shasin.weatherapp.network.api

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import shasin.weatherapp.data.responseModel.WeatherForecastResponse

interface APIService {

    @GET(ApiRoutes.GET_WEATHER_CURRENT)
    fun getCurrentWeather(@Query("key") key: String, @Query("q") q: String): Call<Response>

    @GET(ApiRoutes.GET_WEATHER_FORECAST)
    fun getForecastWeather(@Query("key") key: String, @Query("q") q: String,
                           @Query("days") days: Int): Call<WeatherForecastResponse>


}