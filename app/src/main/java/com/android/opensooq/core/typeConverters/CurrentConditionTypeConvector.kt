package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.CurrentCondition
import com.android.opensooq.core.models.response.Request
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class CurrentConditionTypeConvector {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToCurrentConditionObjectList(data: String?): List<CurrentCondition>? {
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type =
            object : TypeToken<List<CurrentCondition>?>() {}.getType()
        return gson.fromJson<List<CurrentCondition>>(data, listType)
    }

    @TypeConverter
    fun practiceCurrentConditionListToString(list: List<CurrentCondition>?): String? {
        return gson.toJson(list)
    }
}