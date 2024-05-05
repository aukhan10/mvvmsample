package com.example.intercomtest.data

import com.example.intercomtest.model.UniversityListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UniversityListApi {

    @GET("search")
    suspend fun getUniversityList(
        @Query("country") country: String? = null
    ): Response<List<UniversityListResponse>>
}