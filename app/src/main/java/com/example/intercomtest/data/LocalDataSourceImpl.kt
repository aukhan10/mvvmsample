package com.example.intercomtest.data

import com.example.intercomtest.db.UniversityDao
import com.example.intercomtest.mapper.UniversityListMapper
import com.example.intercomtest.model.UniversityListResponse
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val universityDao: UniversityDao,
    private val mapper: UniversityListMapper
) : LocalDataSource {
    override suspend fun getUniversityList(): List<UniversityListResponse> {
        return mapper.mapEntityToItem(universityDao.getAllItems())
    }

    override suspend fun insertUniversityList(items: List<UniversityListResponse>) {
        universityDao.insertAllItems(mapper.mapItemToEntity(items))
    }

    override suspend fun removeAll() {
        universityDao.deleteAll()
    }
}