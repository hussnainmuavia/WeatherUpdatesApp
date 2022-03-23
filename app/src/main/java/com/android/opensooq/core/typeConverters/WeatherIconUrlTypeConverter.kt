package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.WeatherIconUrl
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class WeatherIconUrlTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToWeatherObjectList(data: String?): List<WeatherIconUrl>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<WeatherIconUrl>?>() {}.type
        return gson.fromJson<List<WeatherIconUrl>>(data, listType)
    }

    @TypeConverter
    fun practiceWeatherListToString(list: List<WeatherIconUrl>?): String? {
        return gson.toJson(list)
    }
}