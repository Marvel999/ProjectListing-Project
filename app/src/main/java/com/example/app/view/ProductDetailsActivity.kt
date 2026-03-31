package com.example.app.view

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil3.compose.AsyncImage
import com.example.app.base.AppContents
import com.example.app.base.UiState
import com.example.app.entity.ProductDetailsRes
import com.example.app.view.ProductListActivity.Companion.TAG
import com.example.app.view.ui.theme.AndroidStarterTheme
import com.example.app.viewmodel.ProductDetailsViewmodel
import com.example.app.viewmodel.ProductListingViewmodel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id: Int = intent.getIntExtra(AppContents.PRODUCT_ID, -1);
        enableEdgeToEdge()
        setContent {
            AndroidStarterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    if (id != -1) {
                        ProducDetailsScreen(
                            modifier = Modifier.padding(innerPadding),
                            id = id
                        )
                    } else {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(
                                text = "Invalid Product id",
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ProducDetailsScreen(modifier: Modifier = Modifier, id: Int) {

    val viewmodel = hiltViewModel<ProductDetailsViewmodel>()
    val productDetials = viewmodel.productDetails.collectAsState()
    LaunchedEffect(Unit) {
        viewmodel.fetchProductDetails(id = id)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        when (productDetials.value) {
            is UiState.Error -> {
                Log.e(TAG, "Error")
                Text(
                    text = (productDetials.value as UiState.Error).msg, modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

            is UiState.Loading -> {
                Log.e(TAG, "Loading")
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }

            is UiState.Success<ProductDetailsRes> ->
                ProductDetailsScreen(productDetailsRes = (productDetials.value as UiState.Success).data)
        }
    }

}

@Composable
fun ProductDetailsScreen(productDetailsRes: ProductDetailsRes) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = productDetailsRes.image,
            contentDescription = productDetailsRes.title ?: "image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.8f)
        )
        Text(text = productDetailsRes.title ?: "", fontSize = 18.sp)
        Text(text = productDetailsRes.price.toString(), fontSize = 12.sp)
        Text(text = productDetailsRes.description ?: "", fontSize = 12.sp)
        Text(text = productDetailsRes.category ?: "", fontSize = 12.sp)
    }
}