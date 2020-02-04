package shasin.weatherapp.di.modules

import dagger.Module
import dagger.android.ContributesAndroidInjector

import shasin.weatherapp.ui.activities.MainActivity

@Suppress("unused")
@Module
abstract class ActivityModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributeMainActivity(): MainActivity
}