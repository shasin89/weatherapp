package shasin.weatherapp.network.environment

import shasin.weatherapp.BuildConfig

class BaseEnvironment : Environment{

    private var baseUrl = BuildConfig.BASE_URL

    override fun getAPIUXWeatherBaseUrl():String {
      return baseUrl
    }
}