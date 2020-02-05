
package shasin.weatherapp.di.modules


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import shasin.weatherapp.di.ViewModelKey
import shasin.weatherapp.viewModels.weatherForecastViewModel.ViewModelFactory
import shasin.weatherapp.viewModels.weatherForecastViewModel.WeatherForeCastViewModelImpl

@Suppress("unused")
@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(WeatherForeCastViewModelImpl::class)
    abstract fun bindWeatherForecastViewModel(weatherForeCastViewModelImpl: WeatherForeCastViewModelImpl): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}
