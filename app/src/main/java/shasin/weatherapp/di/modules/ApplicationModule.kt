package shasin.weatherapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import shasin.weatherapp.BaseApplication
import shasin.weatherapp.network.api.APIService
import shasin.weatherapp.network.environment.BaseEnvironment
import shasin.weatherapp.network.environment.Environment
import shasin.weatherapp.network.http.BaseHttpClient
import shasin.weatherapp.network.http.HttpClient
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class])
class ApplicationModule {

    @Provides
    @Singleton
    fun provideApplicationContext(baseApplication: BaseApplication): Context {
        return baseApplication
    }

    @Provides
    @Singleton
    fun provideRetrofit(httpClient: HttpClient): Retrofit {
        return httpClient.getRetrofit()
    }


    @Provides
    @Singleton
    fun provideAPIService(httpClient: HttpClient): APIService {
        return httpClient.getApiService()
    }

    @Provides
    @Singleton
    fun provideHttpClient(environment: Environment): HttpClient {
        return BaseHttpClient(environment)
    }

    @Provides
    @Singleton
    fun provideEnvironment(): Environment {
        return BaseEnvironment()
    }
}