package com.example.intercomtest.mapper

import com.example.intercomtest.db.UniversityItemEntity
import com.example.intercomtest.model.UniversityListResponse
import javax.inject.Inject

class UniversityListMapper @Inject constructor() {
    fun mapEntityToItem(allItems: List<UniversityItemEntity>): List<UniversityListResponse> {

        return allItems.map {
            UniversityListResponse(
                name = it.name,
                country = it.country,
                stateProvince = it.state
            )
        }
    }

    fun mapItemToEntity(list: List<UniversityListResponse>): List<UniversityItemEntity> {
        return list.map {
            UniversityItemEntity(
                name = it.name.orEmpty(),
                country = it.country.orEmpty(),
                state = it.stateProvince.orEmpty()
            )
        }
    }
}