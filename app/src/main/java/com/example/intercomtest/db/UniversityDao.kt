package com.example.intercomtest.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UniversityDao {
    @Query("SELECT * FROM universityList")
    fun getAllItems(): List<UniversityItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllItems(items: List<UniversityItemEntity>)

    @Query("DELETE FROM universityList")
    fun deleteAll()
}