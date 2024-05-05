package com.example.intercomtest.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.intercomtest.model.UniversityListResponse

@Database(
    entities = [UniversityItemEntity::class],
    version = 1
)
abstract class UniversityDatabase : RoomDatabase() {

    abstract fun getUniversityDao(): UniversityDao

}