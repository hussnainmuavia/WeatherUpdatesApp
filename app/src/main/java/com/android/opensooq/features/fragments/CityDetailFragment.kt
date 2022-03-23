package com.android.opensooq.features.fragments

import android.os.Bundle
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
import com.android.opensooq.core.platform.BaseFragment
import com.android.opensooq.core.utils.DateTimeUtil
import com.android.opensooq.features.adapters.HourlyForecastsAdapter
import com.squareup.picasso.Picasso

class CityDetailFragment : BaseFragment() {

    private val ARG_FAV_CITY = "ARG_FAV_CITY"

    private lateinit var mFavouriteModel: FavouriteModel

    private lateinit var mView: View
    private lateinit var tvCityName: TextView
    private lateinit var tvDateTime: TextView
    private lateinit var ivWeatherIcon: ImageView
    private lateinit var tvTemperature: TextView
    private lateinit var tvClimate: TextView
    private lateinit var tvHumidity: TextView
    private lateinit var tvFeelsLike: TextView
    private lateinit var rvForecasts: RecyclerView
    private lateinit var tvMore: TextView

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param favouriteModel FavouriteModel.
         * @return A new instance of fragment CityDetailFragment.
         */
        @JvmStatic
        fun newInstance(favouriteModel: FavouriteModel) =
            CityDetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_FAV_CITY, favouriteModel)
                }
            }
    }

    override fun layoutId() = R.layout.fragment_city_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFavouriteModel = it.getParcelable(ARG_FAV_CITY)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mView = inflater.inflate(R.layout.fragment_city_detail, container, false)
        tvCityName = mView.findViewById(R.id.tvCityName)
        tvDateTime = mView.findViewById(R.id.tvDateTime)
        ivWeatherIcon = mView.findViewById(R.id.ivWeatherIcon)
        tvTemperature = mView.findViewById(R.id.tvTemperature)
        tvClimate = mView.findViewById(R.id.tvClimate)
        tvHumidity = mView.findViewById(R.id.tvHumidity)
        tvFeelsLike = mView.findViewById(R.id.tvFeelsLike)
        rvForecasts = mView.findViewById(R.id.rvForecasts)
        tvMore = mView.findViewById(R.id.tvMore)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = mFavouriteModel
        val searchResult = item?.searchResult
        val currentCondition = searchResult?.data?.currentCondition?.get(0)
        tvCityName.text = item?.query
        val dateTime = currentCondition?.observationTime?.let {
            "${it} ${DateTimeUtil.getDateTime(DateTimeUtil.COMPLETE_TIME_FORMAT)}"
        }
        tvDateTime.text = dateTime
        val weatherIcon = currentCondition?.weatherIconUrl?.get(0)?.value
        Picasso.get().load(weatherIcon).into(ivWeatherIcon)
        tvTemperature.text = context?.getString(R.string.title_temp, currentCondition?.tempC)
        tvClimate.text = currentCondition?.weatherDesc?.get(0)?.value
        tvHumidity.text =
            context?.getString(R.string.title_humidity, currentCondition?.humidity)
        tvFeelsLike.text =
            context?.getString(R.string.title_feels_like, currentCondition?.feelsLikeC)

        searchResult?.data?.weather?.get(0)?.hourly?.let {
            setHourlyAdapterView(it)
        }
    }

    private fun setHourlyAdapterView(hourly: ArrayList<Hourly>) {
        val hourlyAdapter = HourlyForecastsAdapter(requireContext())
        rvForecasts.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            isNestedScrollingEnabled = false
            adapter = hourlyAdapter
        }
        hourly?.let {
            hourlyAdapter.setHourlyForecasts(it)
        }
    }
}
