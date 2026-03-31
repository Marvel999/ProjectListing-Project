package com.example.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.base.UiState
import com.example.app.data.ProductRepoInterface
import com.example.app.entity.ProductDetailsRes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewmodel @Inject constructor(val productRepoInterface: ProductRepoInterface):ViewModel() {

    companion object{
        const val TAG = "ProductListingViewmodel"
    }
    private val _productDetails = MutableStateFlow<UiState<ProductDetailsRes>>(UiState.Loading)
    val productDetails:StateFlow<UiState<ProductDetailsRes>> = _productDetails

    fun fetchProductDetails(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
             productRepoInterface.getProductDetails(id).catch { e->
                 _productDetails.value=UiState.Error(e.message.toString())
                 Log.e(TAG, "Error"+e.message.toString())
            }.collect {
                 _productDetails.value= UiState.Success(it)
                 Log.e(TAG, "Success"+it.toString())

             }
        }
    }

}