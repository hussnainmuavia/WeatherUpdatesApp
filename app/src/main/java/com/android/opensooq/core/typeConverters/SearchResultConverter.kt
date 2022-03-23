package com.android.opensooq.core.typeConverters

import androidx.room.TypeConverter
import com.android.opensooq.core.models.response.SearchResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class SearchResultConverter {

    var gson: Gson = Gson()

    @TypeConverter
    fun stringToRequestObjectList(data: String?): SearchResult? {
        if (data == null) {
            return SearchResult()
        }
        val listType: Type =
            object : TypeToken<SearchResult?>() {}.getType()
        return gson.fromJson<SearchResult>(data, listType)
    }

    @TypeConverter
    fun practiceRequestListToString(list: SearchResult?): String? {
        return gson.toJson(list)
    }
}