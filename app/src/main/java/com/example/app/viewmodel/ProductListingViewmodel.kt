package com.example.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.app.base.UiState
import com.example.app.data.ProductRepoInterface
import com.example.app.entity.ProductListResItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListingViewmodel @Inject constructor(val productRepoInterface: ProductRepoInterface):ViewModel() {

    companion object{
        const val TAG = "ProductListingViewmodel"
    }
    private val _productList = MutableStateFlow<UiState<List<ProductListResItem>>>(UiState.Loading)
    val productList:StateFlow<UiState<List<ProductListResItem>>> = _productList

    fun fetchProductList(){
        viewModelScope.launch {
             productRepoInterface.getProductList().catch { e->
                 _productList.value=UiState.Error(e.message.toString())
                 Log.e(TAG, "Error"+e.message.toString())
            }.collect {
                 _productList.value= UiState.Success(it)
                 Log.e(TAG, "Success"+it.toString())

             }
        }
    }

}