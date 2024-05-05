package com.example.intercomtest.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UniversityListResponse(
    var alphaTwoCode: String? = null,
    var name: String? = null,
    var country: String? = null,
    var domains: List<String> = mutableListOf(),
    var webPages: List<String> = mutableListOf(),
    @Json(name = "state-province") var stateProvince: String? = null
)