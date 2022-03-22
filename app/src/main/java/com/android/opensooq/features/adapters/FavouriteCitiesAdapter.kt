package com.android.opensooq.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.opensooq.R
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.features.callbacks.OnItemClickListener
import com.squareup.picasso.Picasso

class FavouriteCitiesAdapter(private val context: Context) :
    RecyclerView.Adapter<FavCitiesViewHolder>() {

    private var mOnItemClickListener: OnItemClickListener? = null
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
        holder.tvDateTime.text = currentCondition?.observationTime
        val weatherIcon = currentCondition?.weatherIconUrl?.get(0)?.value
        Picasso.get().load(weatherIcon).into(holder.ivWeatherIcon)
        holder.tvTemperature.text = context.getString(R.string.title_temp, currentCondition?.tempC)
        holder.tvClimate.text = currentCondition?.weatherDesc?.get(0)?.value
        holder.tvHumidity.text = context.getString(R.string.title_humidity, currentCondition?.humidity)
        holder.tvFeelsLike.text = context.getString(R.string.title_feels_like, currentCondition?.feelsLikeC)
        //holder.rvForecasts

        holder.tvMore?.setOnClickListener {

        }

        holder.itemView.setOnClickListener {
            this.mOnItemClickListener?.onUserItemClickListener(position)
        }
    }

    override fun getItemCount(): Int {
        return if (mFavouriteModel?.isNotEmpty() == true) {
            mFavouriteModel?.size!!
        } else {
            0
        }
    }
}

class FavCitiesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val ivLocationIcon: ImageView = view.findViewById(R.id.ivLocationIcon)
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