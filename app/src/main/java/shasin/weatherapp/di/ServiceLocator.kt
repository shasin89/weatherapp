package shasin.weatherapp.di

/**
 * A simple singleton class tha provides dependencies to the requester
 */

object ServiceLocator{
//    private var retrofit: Retrofit? = null
//    private var environment: Environment? = null
//    private var baseHttpClient: BaseHttpClient? = null
//    private var weatherForecastRepository:WeatherForecastRepository? = null
//
//    fun provideEnvironment(): Environment {
//        if(environment == null){
//            environment = BaseEnvironment()
//        }
//        return environment!!
//    }
//
//    fun provideRetrofitClient(): Retrofit{
//        if(retrofit == null){
//            retrofit = provideHttpClient().getRetrofit()
//        }
//        return retrofit!!
//    }
//
//
//    fun provideHttpClient(): HttpClient {
//        if(baseHttpClient == null){
//            baseHttpClient = BaseHttpClient(provideEnvironment())
//        }
//        return baseHttpClient!!
//    }
//
//    fun provideWeatherForecastRepo(): WeatherForecastRepository {
//        if(weatherForecastRepository == null){
//            weatherForecastRepository = WeatherForecastRepositoryImpl(provideHttpClient().getApiService())
//        }
//        return weatherForecastRepository!!
//    }
}