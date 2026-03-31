package com.example.app.data

import com.example.app.entity.ProductDetailsRes
import com.example.app.entity.ProductListResItem
import kotlinx.coroutines.flow.Flow

interface ProductRepoInterface {

    suspend fun getProductList():Flow<List<ProductListResItem>>
    suspend fun getProductDetails(id:Int):Flow<ProductDetailsRes>
}