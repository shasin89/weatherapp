package shasin.weatherapp.data.responseModel

import shasin.weatherapp.data.model.CurrentWeather
import shasin.weatherapp.data.model.ForecastDays
import shasin.weatherapp.data.model.Location

class WeatherForecastResponse{
    var location:Location?=null
    var current:CurrentWeather?=null
    var forecast: ForecastDays?=null
}