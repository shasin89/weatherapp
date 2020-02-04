package shasin.weatherapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector
import shasin.weatherapp.ui.fragments.WeatherForecastFragment

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector
    abstract fun contributeWeatherForecastFragment(): WeatherForecastFragment

}