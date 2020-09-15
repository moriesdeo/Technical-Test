package com.example.axiata.entity

import com.google.gson.annotations.SerializedName

data class BaseResponse<DATA>(

    @field:SerializedName("dates")
    val dates: Dates? = null,

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("results")
    val results: DATA? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null
)