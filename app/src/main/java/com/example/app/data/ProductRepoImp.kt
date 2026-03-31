package com.example.app.data

import com.example.app.entity.ProductDetailsRes
import com.example.app.entity.ProductListResItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepoImp @Inject constructor(val productService: ProductService) :ProductRepoInterface {
    override suspend fun getProductList(): Flow<List<ProductListResItem>> {
        return flow {
            emit(productService.getProductList())
        }
    }

    override suspend fun getProductDetails(id: Int): Flow<ProductDetailsRes> {
        return flow {
            emit(productService.getProductDetails(id))
        }
    }


}