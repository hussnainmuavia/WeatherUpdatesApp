package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.Request
import com.android.opensooq.core.models.response.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToWeatherObjectList(data: String?): List<Weather>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Weather>?>() {}.type
        return gson.fromJson<List<Weather>>(data, listType)
    }

    @TypeConverter
    fun practiceWeatherListToString(list: List<Weather>?): String? {
        return gson.toJson(list)
    }
}