package shasin.weatherapp.ui.fragments

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import shasin.weatherapp.SingleFragmentActivity
import shasin.weatherapp.data.model.ForecastDays
import shasin.weatherapp.network.api.Resource
import shasin.weatherapp.utils.DataBindingIdlingResourceRule
import shasin.weatherapp.utils.TaskExecutorWithIdlingResourceRule
import shasin.weatherapp.utils.ViewModelUtil
import shasin.weatherapp.viewModels.weatherForecastViewModel.WeatherForeCastViewModelImpl

@MediumTest
@RunWith(AndroidJUnit4::class)
class WeatherFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(SingleFragmentActivity::class.java)

    @Rule
    @JvmField
    val taskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val dataBindingIdlingResourceRule = DataBindingIdlingResourceRule(activityRule)

    @Rule
    @JvmField
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var viewModel: WeatherForeCastViewModelImpl

    private val testFragment = TestFragment()

    private val forecastDays = MutableLiveData<ForecastDays>()

    @Before
    fun setUp() {
        `when`(viewModel.getForeCastWeathers()).thenReturn(forecastDays)
        testFragment.viewModelFactory = ViewModelUtil.createFor(viewModel)
        activityRule.activity.setFragment(testFragment)
    }

    @Test
    fun isLoading(){
        viewModel.processResponse(Resource.loading(null))
//        onView(withId(R.id.loadingLayout)).check(matches(isDisplayed()))
//        onView(withId(R.id.errorLayout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
//        onView(withId(R.id.weatherLayout)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }


    class TestFragment : WeatherForecastFragment()
}
