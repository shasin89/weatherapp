package shasin.weatherapp.network.http

import retrofit2.Retrofit
import shasin.weatherapp.network.api.APIService

interface HttpClient {
    fun getApiService() : APIService
    fun getRetrofit(): Retrofit
}