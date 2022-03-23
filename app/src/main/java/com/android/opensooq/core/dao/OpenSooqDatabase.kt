package com.android.opensooq.core.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.opensooq.core.models.request.FavouriteModel
import com.android.opensooq.core.models.response.*
import com.android.opensooq.core.typeConverters.*

@Database(
    entities = [
        FavouriteModel::class,
        Astronomy::class,
        ClimateAverage::class,
        CurrentCondition::class,
        Data::class,
        Hourly::class,
        Month::class,
        Request::class,
        SearchResult::class,
        Weather::class,
        WeatherDesc::class,
        WeatherIconUrl::class
    ], version = 2, exportSchema = false
)
@TypeConverters(
    RequestTypeConverter::class,
    CurrentConditionTypeConvector::class,
    WeatherTypeConverter::class,
    MonthTypeConverter::class,
    WeatherIconUrlTypeConverter::class,
    WeatherDescTypeConverter::class,
    ClimateAverageTypeConverter::class,
    HourlyTypeConverter::class
)
abstract class OpenSooqDatabase : RoomDatabase() {

    private var mInstance: OpenSooqDatabase? = null

    abstract fun openSooqDao(): OpenSooqDao

    companion object {

        private var INSTANCE: OpenSooqDatabase? = null
        private val DATABASE_NAME = "OPEN_SOOQ_DB"

        @Synchronized
        fun getDatabaseInstance(context: Context): OpenSooqDatabase? {
            if (INSTANCE == null) {
                synchronized(OpenSooqDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        OpenSooqDatabase::class.java, DATABASE_NAME
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }
    }

    fun destroyInstance() {
        mInstance = null
    }
}