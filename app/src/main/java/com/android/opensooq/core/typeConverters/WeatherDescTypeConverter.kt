package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.WeatherDesc
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherDescTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToWeatherObjectList(data: String?): List<WeatherDesc>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<WeatherDesc>?>() {}.type
        return gson.fromJson<List<WeatherDesc>>(data, listType)
    }

    @TypeConverter
    fun practiceWeatherListToString(list: List<WeatherDesc>?): String? {
        return gson.toJson(list)
    }
}