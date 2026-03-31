package com.example.app.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class RatingX(
    @Json(name = "count")
    var count: Int? = null,
    @Json(name = "rate")
    var rate: Double? = null
)