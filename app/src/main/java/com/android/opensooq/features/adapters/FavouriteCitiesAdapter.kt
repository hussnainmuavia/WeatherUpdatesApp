package com.android.opensooq.features.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.opensooq.R
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.features.callbacks.OnItemClickListener

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

}