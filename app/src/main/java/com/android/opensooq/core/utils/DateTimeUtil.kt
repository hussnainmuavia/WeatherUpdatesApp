package com.android.opensooq.core.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    const val TIME_FORMAT = "h aa"
    const val COMPLETE_TIME_FORMAT = "MMMM dd, yyyy"

    fun getTime(time : String, format : String) : String{
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = time.toInt() / 100
        val sdf = SimpleDateFormat(format)
        return sdf.format(calendar.time)
    }

    fun getDateTime(format : String) : String{
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat(format)
        return sdf.format(calendar.time)
    }
}