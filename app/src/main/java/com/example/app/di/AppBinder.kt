package com.example.app.di

import com.example.app.data.ProductRepoImp
import com.example.app.data.ProductRepoInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBinder {

    @Binds
    abstract fun getProductRepo(productRepoImp: ProductRepoImp):ProductRepoInterface
}