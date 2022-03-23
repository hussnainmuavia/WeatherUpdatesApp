package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.ClimateAverage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class ClimateAverageTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToClimateAverageObjectList(data: String?): List<ClimateAverage>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<ClimateAverage>?>() {}.getType()
        return gson.fromJson<List<ClimateAverage>>(data, listType)
    }

    @TypeConverter
    fun practiceClimateAverageListToString(list: List<ClimateAverage>?): String? {
        return gson.toJson(list)
    }
}