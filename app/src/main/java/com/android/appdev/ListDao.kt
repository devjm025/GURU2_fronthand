package com.android.appdev

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ListDao {
    @Query("SELECT * FROM bucketlist ORDER BY id DESC")
    fun getAll() : LiveData<List<BucketList>>

    @Query("SELECT * FROM bucketlist WHERE title=:title")
    fun select(title : String):List<BucketList>

    @Query("UPDATE bucketlist SET image=:image, category=:category,progress=:progress,Dday=:Dday, info =:info WHERE title=:title")
    fun updateList(title: String, image : String, category: String, progress : Int, Dday : String, info: String)

    @Query("DELETE FROM bucketlist WHERE title =:title")
    fun deleteBucketListById(title: String)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(blist : BucketList)

    @Update
    fun update(blist: BucketList)

    @Delete
    fun delete(blist: BucketList)

    @Query("DELETE FROM bucketlist")
    fun deleteAll()
}