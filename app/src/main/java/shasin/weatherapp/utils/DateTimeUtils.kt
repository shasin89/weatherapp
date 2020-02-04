package shasin.weatherapp.utils

import java.text.SimpleDateFormat

class DateTimeUtils{
    companion object {
        fun getDay(date:String):String{
            val dateTime = SimpleDateFormat("yyyy-MM-dd").parse(date)
            val formatedDate = SimpleDateFormat("EEEE").format(dateTime)
            return formatedDate
        }
    }
}