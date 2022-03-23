package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.Month
import com.android.opensooq.core.models.response.Request
import com.android.opensooq.core.models.response.Weather
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class MonthTypeConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToMonthObjectList(data: String?): List<Month>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Month>?>() {}.type
        return gson.fromJson<List<Month>>(data, listType)
    }

    @TypeConverter
    fun practiceMonthListToString(list: List<Month>?): String? {
        return gson.toJson(list)
    }
}