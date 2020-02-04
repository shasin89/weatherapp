package shasin.weatherapp.ui

import android.databinding.BindingAdapter
import android.view.View
import android.widget.TextView
import shasin.weatherapp.utils.DateTimeUtils

@BindingAdapter("app:showFormattedDay")
fun showFormattedDate(view: TextView, date: String?) {
   if (date.isNullOrEmpty()) view.visibility = View.GONE else view.text = DateTimeUtils.getDay(date)
}

@BindingAdapter("app:showFormatAvgTemp")
fun showFormatAvgTemp(view: TextView, avgTemp: Float?) {
   if (avgTemp == null) view.visibility = View.GONE else view.text = avgTemp.toInt().toString() + " C"
}