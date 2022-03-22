package com.android.opensooq.core.dao

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

/*@Database(entities = [Items::class, FavouriteTracks::class, FavouriteSong::class], version = 2, exportSchema = false)
@TypeConverters(
    AlbumConvertor::class,
    Album_Converter::class,
    ArtistConverter::class,
    Artist_Converter::class,
    CoverConverter::class,
    Cover_Converter::class,
    ItemsConverter::class,
    Ms247Converter::class,
    TrackConverter::class,
    TracksConvertor::class
)*/
abstract class OpenSooqDatabase : RoomDatabase() {

    private var mInstance: OpenSooqDatabase? = null

    abstract fun musicDao(): OpenSooqDao

    companion object {
        private var INSTANCE: OpenSooqDatabase? = null
        private val DATABASE_NAME = "sample-database"

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