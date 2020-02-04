package shasin.weatherapp.utils

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import shasin.weatherapp.BaseApplication
import shasin.weatherapp.network.api.ResourceError
import javax.inject.Inject

class ErrorUtils{

    @Inject
    lateinit var retrofit: Retrofit

    init {
        retrofit = BaseApplication.getInstance().applicationComponent.getRetrofit()
    }

    val API_KEY_NOT_PROVIDED:Int = 1002
    val PARAMETER_Q_INVALID:Int = 1003
    val REQUEST_URL_NOT_VALID:Int = 1005
    val NO_LOCATION_FOUND:Int = 1006
    val API_KEY_NOT_VALID:Int = 2006
    val API_KEY_EXCEED_QUOTA:Int = 2007
    val API_KEY_DISABLED:Int = 2008


    fun parseError(responseErrorBody: ResponseBody?,code:Int): ResourceError {
        var converter: Converter<ResponseBody, ResourceError> =
            retrofit
                .responseBodyConverter(ResourceError::class.java, arrayOfNulls<Annotation>(0));
        var error = ResourceError()
        try {
            error = converter.convert(responseErrorBody!!)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return checkErrorCode(error)
    }

    private fun checkErrorCode(error: ResourceError): ResourceError {

        // if custom error message require
//        when (error.error!!.code) {
//            API_KEY_NOT_VALID -> { error.error!!.message }
//          else -> error
//        }
        return error
    }
}