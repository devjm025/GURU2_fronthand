package com.android.appdev

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bucketlist")

data class BucketList (
    @PrimaryKey(autoGenerate = true)
    var id : Long?,

    @ColumnInfo(name="image")
    var image : String?,

    @ColumnInfo(name="category")
    var category:String?,

    @ColumnInfo(name = "progress")
    var progress: Int?,

    @ColumnInfo(name = "Dday")
    var Dday : String?,

    @ColumnInfo(name="title")
    var title: String?,

    @ColumnInfo(name = "info")
    var info : String?
        )