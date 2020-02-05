package shasin.weatherapp

import android.app.Activity
import android.app.Application
import android.app.Service
import dagger.android.HasActivityInjector
import shasin.weatherapp.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasServiceInjector
import shasin.weatherapp.di.components.ApplicationComponent
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector , HasServiceInjector{

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingServiceInjector: DispatchingAndroidInjector<Service>

    @Inject
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
            singleton = this
            AppInjector.init(this)
    }

    override fun activityInjector() = dispatchingAndroidInjector

    override fun serviceInjector(): AndroidInjector<Service> {
        return dispatchingServiceInjector
    }

    companion object {
        // Singleton Instance
        private lateinit var singleton: BaseApplication

        fun getInstance() : BaseApplication { return singleton }
    }

}