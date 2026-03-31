package com.example.app.base

sealed class UiState<out T> {

    data class Error(val msg:String):UiState<Nothing>()
    data class Success<T>(val data:T):UiState<T>()
    object Loading:UiState<Nothing>()
}