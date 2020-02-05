package shasin.weatherapp.ui.adapters

import androidx.databinding.DataBindingUtil
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shasin.weatherapp.R
import shasin.weatherapp.data.model.ForecastDay
import shasin.weatherapp.databinding.ItemForecastdayCellBinding


class ForecastDayAdapter(var models: ArrayList<ForecastDay>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemForecastdayCellBinding> (layoutInflater, LAYOUT_PLACES_ITEM, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val model = models[position]
            holder.binding.model = model
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }

    class ViewHolder: RecyclerView.ViewHolder {
        lateinit var binding: ItemForecastdayCellBinding

        constructor(view: View) : super(view)

        constructor(binding: ItemForecastdayCellBinding) : this(binding.root) {
            this.binding = binding
        }
    }

    companion object {
        @LayoutRes
        val LAYOUT_PLACES_ITEM = R.layout.item_forecastday_cell
    }
}