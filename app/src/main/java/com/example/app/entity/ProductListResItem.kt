package com.example.app.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep
import androidx.compose.runtime.Stable

@Keep
@JsonClass(generateAdapter = true)
@Stable
data class ProductListResItem(
    @Json(name = "category")
    var category: String? = null,
    @Json(name = "description")
    var description: String? = null,
    @Json(name = "id")
    var id: Int? = null,
    @Json(name = "image")
    var image: String? = null,
    @Json(name = "price")
    var price: Double? = null,
    @Json(name = "rating")
    var rating: Rating? = null,
    @Json(name = "title")
    var title: String? = null
)