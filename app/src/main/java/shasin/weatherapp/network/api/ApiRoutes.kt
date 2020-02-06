package shasin.weatherapp.network.api

import shasin.weatherapp.BuildConfig

interface ApiRoutes {

    companion object {
        var API_KEY = BuildConfig.API_KEY
        const val GET_WEATHER_FORECAST = "forecast.json?"
        const val GET_WEATHER_CURRENT = "current.json?"

    }
}