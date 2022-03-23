package com.android.opensooq.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.opensooq.R
import com.android.opensooq.core.models.response.Hourly
import com.android.opensooq.core.utils.DateTimeUtil
import com.android.opensooq.core.utils.DateTimeUtil.TIME_FORMAT
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class HourlyForecastsAdapter(private val context: Context) :
    RecyclerView.Adapter<HourlyViewHolder>() {

    private lateinit var mOnItemClickListener: OnItemClickListener
    private lateinit var mHourly: ArrayList<Hourly>

    fun setHourlyForecasts(hourly: ArrayList<Hourly>) {
        this.mHourly = hourly
        notifyDataSetChanged()
    }

    fun registerCallback(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, positon: Int): HourlyViewHolder {
        return HourlyViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_hourly_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {

        val item = mHourly[position]
        val weatherIcon = item.weatherIconUrl?.get(0)?.value
        Picasso.get().load(weatherIcon).into(holder.ivWeatherIcon)
        holder.tvTime.text = item.time?.let { DateTimeUtil.getTime(it, TIME_FORMAT) }
        holder.tvTemperature.text = context.getString(R.string.title_temp, item.tempC)
        holder.tvTemperature.text = context.getString(R.string.title_temp, item.tempC)
        holder.tvHumidity.text = context.getString(R.string.title_humidity_percent, item.humidity)

        holder.itemView.setOnClickListener {
            this.mOnItemClickListener.onUserItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return if (mHourly.isNotEmpty()) {
            mHourly.size
        } else {
            0
        }
    }
}

class HourlyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvTime: TextView = view.findViewById(R.id.tvTime)
    val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
    val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
    val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
}