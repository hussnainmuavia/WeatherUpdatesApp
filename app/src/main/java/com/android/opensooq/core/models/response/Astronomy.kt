package com.android.opensooq.core.models.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Astronomy(
    @SerializedName("sunrise")
    @Expose
    var sunrise: String? = null,

    @SerializedName("sunset")
    @Expose
    var sunset: String? = null,

    @SerializedName("moonrise")
    @Expose
    var moonrise: String? = null,

    @SerializedName("moonset")
    @Expose
    var moonset: String? = null,

    @SerializedName("moon_phase")
    @Expose
    var moonPhase: String? = null,

    @SerializedName("moon_illumination")
    @Expose
    var moonIllumination: String? = null
)