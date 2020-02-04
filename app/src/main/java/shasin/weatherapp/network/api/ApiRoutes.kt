package shasin.weatherapp.network.api

import shasin.weatherapp.BuildConfig

interface ApiRoutes {

    companion object {
        var API_KEY = BuildConfig.API_KEY
        const val GET_WEATHER_FORECAST = "v1/forecast.json?"
        const val GET_WEATHER_CURRENT = "v1/current.json?"
        const val GET_WEATHER_HISTORY = "v1/history.json?"
        const val GET_WEATHER_SEARCH = "v1/search.json?"

    }
}