package com.example.intercomtest.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "universityList")
data class UniversityItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val name: String,
    val country: String,
    val state: String
)
