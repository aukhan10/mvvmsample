package com.example.intercomtest.data

import com.example.intercomtest.model.UniversityListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UniversityListRepository @Inject constructor(
    private val universityListDataSource: UniversityListDataSource,
    private val localDataSource: LocalDataSource
) {
    suspend fun getUniversityList(country: String): List<UniversityListResponse> {
        var list = universityListDataSource.getUniversityList(country)

        withContext(Dispatchers.IO) {
            if (list.isNotEmpty()) {
                insertListToDb(list)
            } else {
                list = getListFromDb()
            }
        }
        return list
    }

    private suspend fun insertListToDb(list: List<UniversityListResponse>) {
        localDataSource.removeAll()
        localDataSource.insertUniversityList(list)
    }

    private suspend fun getListFromDb(): List<UniversityListResponse> {
        return localDataSource.getUniversityList()
    }
}