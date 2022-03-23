package com.android.opensooq.features.callbacks

interface OnItemClickListener {

    fun onItemClickListener(position: Int, any: Any)

    fun onMoreClickListener(position: Int, any: Any)
}