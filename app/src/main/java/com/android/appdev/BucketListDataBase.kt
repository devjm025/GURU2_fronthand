package com.android.appdev

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(BucketList::class), version = 1, exportSchema = false)
abstract class BucketListDataBase : RoomDatabase() {
    abstract fun listDao() : ListDao

    companion object{
        private var INSTANCE:BucketListDataBase?=null
        fun getInstance(context: Context) : BucketListDataBase{
            if(INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    BucketListDataBase::class.java,
                    "bucketlist_database")
                    .allowMainThreadQueries().build()
            }
            return INSTANCE as BucketListDataBase
        }
    }
}