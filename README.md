# Android Machine Coding Starter

This is a minimal starter project for interview setup.

Issue was 

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

I forgot to  emit()



Instructions:
1. Open in Android Studio
2. Ensure it builds successfully
3. Do NOT implement any specific use case beforehand

You are free to use any architecture and language (Kotlin/Java).
