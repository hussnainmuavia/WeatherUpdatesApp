package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Month(
    @SerializedName("index")
    @Expose
    var index: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("avgMinTemp")
    @Expose
    var avgMinTemp: String? = null,

    @SerializedName("avgMinTemp_F")
    @Expose
    var avgMinTempF: String? = null,

    @SerializedName("absMaxTemp")
    @Expose
    var absMaxTemp: String? = null,

    @SerializedName("absMaxTemp_F")
    @Expose
    var absMaxTempF: String? = null,

    @SerializedName("avgDailyRainfall")
    @Expose
    var avgDailyRainfall: String? = null
)