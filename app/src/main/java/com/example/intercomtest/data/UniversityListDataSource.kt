package com.example.intercomtest.data

import com.example.intercomtest.model.UniversityListResponse
import retrofit2.Response

interface UniversityListDataSource {
    suspend fun getUniversityList(country: String): List<UniversityListResponse>
}