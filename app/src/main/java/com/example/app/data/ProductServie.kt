package com.example.app.data

import com.example.app.entity.ProductDetailsRes
import com.example.app.entity.ProductListResItem
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
//    https://fakestoreapi.com/products/1
//    https://fakestoreapi.com/products?limit=10

    @GET("/products/")
    suspend fun getProductList(@Query("limit") limit:Int =10):List<ProductListResItem>

    @GET("/products/{id}")
    suspend fun getProductDetails(@Path("id") id: Int):ProductDetailsRes

}