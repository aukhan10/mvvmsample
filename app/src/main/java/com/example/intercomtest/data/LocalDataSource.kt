package com.example.intercomtest.data

import com.example.intercomtest.model.UniversityListResponse

interface LocalDataSource {
    suspend fun getUniversityList(): List<UniversityListResponse>

    suspend fun insertUniversityList(items: List<UniversityListResponse>)

    suspend fun removeAll()
}