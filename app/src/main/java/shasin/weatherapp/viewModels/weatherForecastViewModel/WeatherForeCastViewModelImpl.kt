package shasin.weatherapp.viewModels.weatherForecastViewModel

import androidx.lifecycle.*
import shasin.weatherapp.data.model.ForecastDays
import shasin.weatherapp.data.responseModel.WeatherForecastResponse
import shasin.weatherapp.network.api.Resource
import shasin.weatherapp.network.api.ResourceError
import shasin.weatherapp.repository.WeatherForecastRepository
import javax.inject.Inject

class WeatherForeCastViewModelImpl @Inject constructor(var weatherForecastRepository: WeatherForecastRepository):
    WeatherforeCastViewModel, ViewModel(){

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    private val isError: MutableLiveData<Boolean> = MutableLiveData()
    private val isSuccess: MutableLiveData<Boolean> = MutableLiveData()
    private val error: MutableLiveData<ResourceError> = MutableLiveData()
    private val weatherForecastResponse:MutableLiveData<WeatherForecastResponse> = MutableLiveData()
    private val callObserver: Observer<Resource<WeatherForecastResponse>> = Observer { t -> processResponse(t) }

    override fun callForecastWeatherAPI(query:String,days:Int){
        weatherForecastRepository.getForecastWeather(query,days).observeForever { callObserver.onChanged(it)}
    }

    override fun processResponse(response: Resource<WeatherForecastResponse>?){
        when(response?.status){
            Resource.Status.LOADING -> {
                setLoading()
            }
            Resource.Status.SUCCESS -> {
                setSuccess()
                weatherForecastResponse.value = response.data
            }
            Resource.Status.ERROR -> {
                setError()
                error.value = response.resourceError
            }
        }
    }

    override fun isLoading(): LiveData<Boolean> {
       return isLoading
    }
    override fun getForeCastWeathers(): LiveData<ForecastDays> {
        return Transformations.map(weatherForecastResponse){ it?.forecast }
    }

    override fun getError(): LiveData<ResourceError> {
        return error
    }

    override fun getCurrentWeather(): LiveData<String> {
        return Transformations.map(weatherForecastResponse){ it?.current?.temp_c!!.toInt().toString() + "\u00B0" }
    }

    override fun getCurrentLocationName(): LiveData<String> {
       return Transformations.map(weatherForecastResponse){ it?.location?.name }
    }


    fun isError():LiveData<Boolean>{
        return isError
    }

    fun isSuccess():LiveData<Boolean>{
        return isSuccess
    }

    private fun setSuccess(){
        isLoading.value = false
        isSuccess.value = true
        isError.value = false
    }

    private fun setError(){
        isLoading.value = false
        isSuccess.value = false
        isError.value = true
    }

    private fun setLoading(){
        isLoading.value = true
        isSuccess.value = false
        isError.value = false
    }

    public override fun onCleared() {
        super.onCleared()
    }
}