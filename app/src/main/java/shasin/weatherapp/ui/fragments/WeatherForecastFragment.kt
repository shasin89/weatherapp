package shasin.weatherapp.ui.fragments

import android.Manifest
import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import shasin.weatherapp.R
import shasin.weatherapp.data.model.ForecastDays
import shasin.weatherapp.databinding.FragmentWeatherBinding
import shasin.weatherapp.di.Injectable
import shasin.weatherapp.ui.adapters.ForecastDayAdapter
import shasin.weatherapp.viewModels.weatherForecastViewModel.WeatherForeCastViewModelImpl
import javax.inject.Inject

open class WeatherForecastFragment : Fragment(), Injectable{

    private val TAG = WeatherForecastFragment::class.java.simpleName

    lateinit var viewModel: WeatherForeCastViewModelImpl

    @Inject
    @VisibleForTesting
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var forecastDayAdapter: ForecastDayAdapter
    public var query = ""
    public val days = 5
    lateinit var dataBinding: FragmentWeatherBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding =  DataBindingUtil.inflate(inflater ,R.layout.fragment_weather,container , false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setViewModel()
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = this
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setLoadingAnimation()
        setRecycler()
        observeResponse()
        setListener()
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity as Activity)
    }

    private fun observeResponse(){
            viewModel.getForeCastWeathers().observe(this, Observer { it?.let {setForCastDayData(it)}})
    }

    override fun onResume() {
        super.onResume()
        getLastKnownLocation()
//        tempErrorSimulation()
    }


    private fun setViewModel(){
            viewModel =  ViewModelProviders.of(this, viewModelFactory)
                .get(WeatherForeCastViewModelImpl::class.java)
    }

    /*
* Fused location provider is used here to get the current/last known location
* Use places api to get more accurate latlong
* */
    private fun getLastKnownLocation(){
        if(checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
            fusedLocationClient.lastLocation.addOnSuccessListener {
                it?.let {
                    query = it.latitude.toString() + "," + it.longitude.toString()
                        viewModel.callForecastWeatherAPI(query,days)
                }?:run{
                    Toast.makeText(context,getString(R.string.unable_to_get_current_location), Toast.LENGTH_LONG).show()
                }

            }
        }
    }

    private fun tempErrorSimulation(){
//        if(::viewModel.isInitialized)
        viewModel.callForecastWeatherAPI("",days)
    }

    private fun checkPermission(permission: String): Boolean {
        val hasPermission = ContextCompat.checkSelfPermission(context!!, permission) == PackageManager.PERMISSION_GRANTED
        if (!hasPermission) {
            ActivityCompat.requestPermissions(activity!!, arrayOf(permission), 0)
        }
        return hasPermission
    }


    private fun setRecycler(){
        forecastDayAdapter = ForecastDayAdapter(ArrayList())
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = forecastDayAdapter
        recyclerView.layoutManager = layoutManager
        forecastDayAdapter.notifyDataSetChanged()
    }

    private fun setForCastDayData(forecastDays: ForecastDays){
        if(!forecastDays.forecastday.isNullOrEmpty()){
            forecastDays.forecastday.removeAt(0)
        }
        forecastDayAdapter.models = forecastDays.forecastday
        forecastDayAdapter.notifyDataSetChanged()
        bottomLayout.visibility = View.VISIBLE
        bottomLayout.startAnimation(AnimationUtils.loadAnimation(context,R.anim.slide_up))
    }


    private fun setLoadingAnimation(){
        ivLoader.startAnimation(AnimationUtils.loadAnimation(context, R.anim.rotate))
    }

    private fun setListener(){
        btnRety.setOnClickListener { getLastKnownLocation() }
    }
}