package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class RequestTypeConverter {

    var gson: Gson = Gson()

    @TypeConverter
    fun stringToRequestObjectList(data: String?): List<Request>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<Request>?>() {}.getType()
        return gson.fromJson<List<Request>>(data, listType)
    }

    @TypeConverter
    fun practiceRequestListToString(list: List<Request>?): String? {
        return gson.toJson(list)
    }
}