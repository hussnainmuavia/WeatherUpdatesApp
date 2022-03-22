package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ClimateAverage (
    @SerializedName("month")
    @Expose
    var month: List<Month>? = null
)