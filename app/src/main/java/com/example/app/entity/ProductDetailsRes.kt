package com.example.app.entity


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import androidx.annotation.Keep

@Keep
@JsonClass(generateAdapter = true)
data class ProductDetailsRes(
    @Json(name = "category")
    var category: String? = "",
    @Json(name = "description")
    var description: String? = "",
    @Json(name = "id")
    var id: Int? = 0,
    @Json(name = "image")
    var image: String? = "",
    @Json(name = "price")
    var price: Double? = 0.0,
    @Json(name = "rating")
    var rating: RatingX? = RatingX(),
    @Json(name = "title")
    var title: String? = ""
)