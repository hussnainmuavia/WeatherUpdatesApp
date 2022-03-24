package com.android.opensooq.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.opensooq.R
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.core.models.response.Hourly
import com.android.opensooq.core.utils.DateTimeUtil
import com.android.opensooq.core.utils.DateTimeUtil.COMPLETE_TIME_FORMAT
import com.android.opensooq.features.callbacks.OnHourClickListener
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.squareup.picasso.Picasso

class FavouriteCitiesAdapter(private val context: Context) :
    RecyclerView.Adapter<FavCitiesViewHolder>(), OnHourClickListener {

    private lateinit var mOnItemClickListener: OnItemClickListener
    private var mFavouriteModel: ArrayList<FavouriteModel>? = null

    fun setSearchResults(favCities: ArrayList<FavouriteModel>) {
        this.mFavouriteModel = favCities
        notifyDataSetChanged()
    }

    fun registerCallback(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, positon: Int): FavCitiesViewHolder {
        return FavCitiesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_city_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavCitiesViewHolder, position: Int) {

        val item = mFavouriteModel?.get(position)
        val searchResult = item?.searchResult
        val currentCondition = searchResult?.data?.currentCondition?.get(0)
        holder.tvCityName.text = item?.query
        val dateTime = currentCondition?.observationTime?.let {
            it + " " + DateTimeUtil.getDateTime(COMPLETE_TIME_FORMAT)
        }
        holder.tvDateTime.text = dateTime
        val weatherIcon = currentCondition?.weatherIconUrl?.get(0)?.value
        Picasso.get().load(weatherIcon).into(holder.ivWeatherIcon)
        holder.tvTemperature.text = context.getString(R.string.title_temp, currentCondition?.tempC)

        holder.tvClimate.text = currentCondition?.weatherDesc?.get(0)?.value
        holder.tvHumidity.text =
            context.getString(R.string.title_humidity, currentCondition?.humidity)
        holder.tvFeelsLike.text =
            context.getString(R.string.title_feels_like, currentCondition?.feelsLikeC)

        val hourly = searchResult?.data?.weather?.get(position)?.hourly
        setHourlyAdapterView(holder, hourly)

        holder.tvMore.setOnClickListener {
            mOnItemClickListener.onMoreClickListener(position, item!!)
        }

        holder.itemView.setOnClickListener {
            this.mOnItemClickListener.onItemClickListener(position, item!!)
        }
    }

    override fun getItemCount(): Int {
        return if (mFavouriteModel?.isNotEmpty() == true) {
            mFavouriteModel?.size!!
        } else {
            0
        }
    }

    private fun setHourlyAdapterView(
        holder: FavCitiesViewHolder,
        hourly: ArrayList<Hourly>?
    ) {
        val hourlyAdapter = HourlyForecastsAdapter(context)
        holder.rvForecasts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = hourlyAdapter
        }
        hourly?.let {
            hourlyAdapter.setHourlyForecasts(it)
            hourlyAdapter.registerCallback(this)
        }
    }

    override fun onHourClickListener(any: Any, positon: Int) {
        /*
        * Intentionally Blank for demonstration
        * We can add further details or actions to view the specific hours detail
        * */
    }
}

class FavCitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvCityName: TextView = view.findViewById(R.id.tvCityName)
    val tvDateTime: TextView = view.findViewById(R.id.tvDateTime)
    val ivWeatherIcon: ImageView = view.findViewById(R.id.ivWeatherIcon)
    val tvTemperature: TextView = view.findViewById(R.id.tvTemperature)
    val tvClimate: TextView = view.findViewById(R.id.tvClimate)
    val tvHumidity: TextView = view.findViewById(R.id.tvHumidity)
    val tvFeelsLike: TextView = view.findViewById(R.id.tvFeelsLike)
    val rvForecasts: RecyclerView = view.findViewById(R.id.rvForecasts)
    val tvMore: TextView = view.findViewById(R.id.tvMore)
}