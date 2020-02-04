package shasin.weatherapp.network.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import shasin.weatherapp.network.api.APIService
import shasin.weatherapp.network.environment.Environment
import java.util.concurrent.TimeUnit

class BaseHttpClient(var environment: Environment): HttpClient {
    private lateinit var apiService: APIService
    private val okHttpClient: OkHttpClient
    private lateinit var retrofit: Retrofit

    init {
        okHttpClient = createOkHttpClient()
        createServices(okHttpClient)
    }

    private fun createServices(okHttpClient: OkHttpClient) {
        apiService = createAPIService(okHttpClient)
    }

    override fun getApiService(): APIService {
      return apiService
    }

    override fun getRetrofit(): Retrofit {
        return retrofit
    }

    private fun createOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY


        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    private fun createAPIService(client: OkHttpClient): APIService {
        val baseUrl = environment.getAPIUXWeatherBaseUrl()

        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(APIService::class.java)
    }
}