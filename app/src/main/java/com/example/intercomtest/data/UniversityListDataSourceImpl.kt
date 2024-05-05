package com.example.intercomtest.data

import com.example.intercomtest.model.UniversityListResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UniversityListDataSourceImpl @Inject constructor(
    private val universityListApi: UniversityListApi
) : UniversityListDataSource {

    override suspend fun getUniversityList(country: String): List<UniversityListResponse> {
        var list: List<UniversityListResponse> = mutableListOf()
        try {
            var response = universityListApi.getUniversityList(country)
            list = response.body().orEmpty()
        }catch (e: HttpException) {
            // Handle HTTP error response
            val errorBody = e.response()?.errorBody()?.string()
            // Handle error using errorResponse
        } catch (e: IOException) {
            // Handle network error
            e.printStackTrace()
        }
        return list
    }
}