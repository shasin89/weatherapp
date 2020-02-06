package shasin.weatherapp.network.api

import okhttp3.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import shasin.weatherapp.data.responseModel.WeatherForecastResponse

interface APIService {

    @GET(ApiRoutes.GET_WEATHER_CURRENT)
    fun getCurrentWeather(@Query("access_key") key: String, @Query("query") q: String): Call<Response>

    @GET(ApiRoutes.GET_WEATHER_FORECAST)
    fun getForecastWeather(@Query("access_key") key: String, @Query("query") q: String,
                           @Query("forecast_days") days: Int): Call<WeatherForecastResponse>


}