package shasin.weatherapp

import android.app.Activity
import android.app.Application
import dagger.android.DaggerApplication
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import shasin.weatherapp.di.AppInjector
import shasin.weatherapp.di.components.ApplicationComponent
import shasin.weatherapp.di.components.DaggerApplicationComponent
import javax.inject.Inject

class BaseApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var applicationComponent: ApplicationComponent
    private val mLock = Any()

    override fun onCreate() {
        super.onCreate()
        synchronized(mLock) {
            singleton = this
            DaggerApplicationComponent.builder().application(this)
                .build().inject(this)
            AppInjector.init(this)
        }
    }

    override fun activityInjector() = dispatchingAndroidInjector

    companion object {
        // Singleton Instance
        private lateinit var singleton: BaseApplication

        fun getInstance() : BaseApplication { return singleton }
    }

}