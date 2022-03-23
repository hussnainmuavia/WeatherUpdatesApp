package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.Hourly
import com.android.opensooq.core.models.response.Month
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class HourlyTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToHourlyObjectList(data: String?): List<Hourly>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Hourly>?>() {}.type
        return gson.fromJson<List<Hourly>>(data, listType)
    }

    @TypeConverter
    fun practiceHourlyListToString(list: List<Hourly>?): String? {
        return gson.toJson(list)
    }
}