package shasin.weatherapp.di.modules

import dagger.Module
import dagger.Provides
import shasin.weatherapp.network.api.APIService
import shasin.weatherapp.repository.WeatherForecastRepository
import shasin.weatherapp.repository.WeatherForecastRepositoryImpl
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(apiService: APIService): WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(apiService)
    }
}