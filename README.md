
## Interview Demo Video
View is reversed due to emulator issue
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
