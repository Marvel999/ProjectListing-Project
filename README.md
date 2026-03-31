[Screen_recording_20260331_111311.webm](https://github.com/user-attachments/assets/1a5161b2-2a7d-49e5-91bd-f73b20506c00)# Android Machine Coding Starter

This is a minimal starter project for interview setup.


[Screen_recording_20260331_111311.webm](https://github.com/user-attachments/assets/86daacda-cc9c-453e-a6d5-d64dc8aae3a0)

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
