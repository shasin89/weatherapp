package shasin.weatherapp.viewModels.weatherForecastViewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.jraska.livedata.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import shasin.weatherapp.data.model.*
import shasin.weatherapp.data.responseModel.WeatherForecastResponse
import shasin.weatherapp.network.api.Resource
import shasin.weatherapp.network.api.ResourceError
import shasin.weatherapp.repository.WeatherForecastRepository

class WeatherForecastViewModelTest{

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: WeatherForeCastViewModelImpl
    private val lifecycleOwner:LifecycleOwner = mock()
    private val weatherForecastRepository:WeatherForecastRepository = mock()
    private val weatherForecastResponse: MutableLiveData<Resource<WeatherForecastResponse>> = MutableLiveData()
    private val query = "1.29,103.86"
    private val days = 4



    @Before
    @Throws(Exception::class)
    fun setup() {
        viewModel = WeatherForeCastViewModelImpl(weatherForecastRepository)
    }

    @Test
    fun testWeatherForecastAPILoading(){
        //Arrange
        val loadingObserver = viewModel.isLoading().test()
        val errorObserver = viewModel.isError().test()
        val successObserver = viewModel.isSuccess().test()

        //Act
        val resource:Resource<WeatherForecastResponse> = Resource.loading(null)
        weatherForecastResponse.value = resource
        whenever(weatherForecastRepository.getForecastWeather(query,days)).thenReturn(weatherForecastResponse)
        viewModel.callForecastWeatherAPI(query,days)

        //Assert
        loadingObserver.assertHasValue().assertValue(true)
        errorObserver.assertHasValue().assertValue(false)
        successObserver.assertHasValue().assertValue(false)
    }

    @Test
    fun testWeatherForecastAPIError(){
        //Arrange
        val loadingObserver = viewModel.isLoading().test()
        val errorObserver = viewModel.isError().test()
        val successObserver = viewModel.isSuccess().test()
        val resourceErrorObserver = viewModel.getError().test()
        val error = ResourceError().apply {
            error = shasin.weatherapp.data.model.Error(1006,"No location found matching parameter 'q'") }

        //Act
        val resource:Resource<WeatherForecastResponse> = Resource.error(error)
        weatherForecastResponse.value = resource
        whenever(weatherForecastRepository.getForecastWeather(query,days)).thenReturn(weatherForecastResponse)
        viewModel.callForecastWeatherAPI(query,days)

        //Assert
        resourceErrorObserver.assertHasValue().assertValue{ it.error!!.code == 1006 }
        resourceErrorObserver.assertHasValue().assertValue{ it.error!!.message == "No location found matching parameter 'q'" }
        loadingObserver.assertHasValue().assertValue(false)
        errorObserver.assertHasValue().assertValue(true)
        successObserver.assertHasValue().assertValue(false)
    }

    @Test
    fun testWeatherForecastAPISuccess(){
        //Arrange
        val loadingObserver = viewModel.isLoading().test()
        val errorObserver = viewModel.isError().test()
        val successObserver = viewModel.isSuccess().test()
        val currentLocationNameObserver = viewModel.getCurrentLocationName().test()
        val currentWeatherObserver = viewModel.getCurrentWeather().test()
        val forecastWeatherObserver = viewModel.getForeCastWeathers().test()

        val response = WeatherForecastResponse().apply {
            current = CurrentWeather().apply { temp_c = 23.4f }
            location = Location().apply {  name = "Singapore" }
            forecast = ForecastDays().apply {  forecastday.add(ForecastDay()) }
        }

        //Act
        val resource:Resource<WeatherForecastResponse> = Resource.success(response)
        weatherForecastResponse.value = resource
        whenever(weatherForecastRepository.getForecastWeather(query,days)).thenReturn(weatherForecastResponse)
        viewModel.callForecastWeatherAPI(query,days)

        //Assert
        currentLocationNameObserver.assertHasValue().assertValue{ it == "Singapore" }
        currentWeatherObserver.assertHasValue().assertValue {it == "23" + "\u00B0" }
        forecastWeatherObserver.assertHasValue().assertValue{ it.forecastday.size == 1 }
        loadingObserver.assertHasValue().assertValue(false)
        errorObserver.assertHasValue().assertValue(false)
        successObserver.assertHasValue().assertValue(true)
    }


    @Test
    fun testViewModelOnCleared(){
        //Arrange
        //Act
        viewModel.onCleared()
        //Assert
    }
}